package com.hmk.challenge.bank.dataprovider;

import com.hmk.challenge.bank.business.domain.Transaction;
import com.hmk.challenge.bank.business.port.out.TransactionDataProvider;
import com.hmk.challenge.bank.dataprovider.converter.TransactionConverter;
import com.hmk.challenge.bank.dataprovider.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class TransactionDataProviderImpl implements TransactionDataProvider {

    private final TransactionRepository repository;

    @Override
    public Transaction save(Transaction transaction) {
        var transactionEntity = TransactionConverter.from(transaction);
        return TransactionConverter.to(repository.save(transactionEntity));
    }

}
