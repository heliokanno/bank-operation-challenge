package com.hmk.challenge.bank.business.usecase;

import com.hmk.challenge.bank.business.domain.Transaction;
import com.hmk.challenge.bank.business.domain.exception.NotFoundException;
import com.hmk.challenge.bank.business.domain.exception.UnauthorizedTransactionException;
import com.hmk.challenge.bank.business.port.in.SendTransferUseCase;
import com.hmk.challenge.bank.business.port.out.AccountDataProvider;
import com.hmk.challenge.bank.business.port.out.NotifyTransactionClient;
import com.hmk.challenge.bank.business.port.out.TransactionAuthorizeClient;
import com.hmk.challenge.bank.business.port.out.TransactionDataProvider;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class SendTransferUseCaseImpl implements SendTransferUseCase {
    private final TransactionDataProvider transactionDataProvider;
    private final AccountDataProvider accountDataProvider;
    private final TransactionAuthorizeClient transactionAuthorizeClient;
    private final NotifyTransactionClient notifyTransactionClient;

    @Transactional
    @Override
    public Transaction execute(Transaction transaction) {
        transaction.selfValidation();

        var authorized = transactionAuthorizeClient.authorize(transaction.getSourceAccountId(), transaction.getAmount());
        if (!authorized) {
            throw new UnauthorizedTransactionException();
        }

        var sourceAccount = accountDataProvider.findByIdAndLockForWrite(transaction.getSourceAccountId())
                .orElseThrow(() -> new NotFoundException("Source account not found"));

        var targetAccount = accountDataProvider.findByIdAndLockForWrite(transaction.getTargetAccountId())
                .orElseThrow(() -> new NotFoundException("Target account not found"));

        sourceAccount.withdraw(transaction.getAmount());
        targetAccount.deposit(transaction.getAmount());

        accountDataProvider.save(sourceAccount);
        accountDataProvider.save(targetAccount);

        transaction = transactionDataProvider.save(transaction);
        notifyTransactionClient.send(sourceAccount.getId(), "Transaction sent successfully");
        notifyTransactionClient.send(targetAccount.getId(), "Transaction received successfully");
        return transaction;
    }
}
