package com.hmk.challenge.bank.business.usecase;

import com.hmk.challenge.bank.business.domain.Account;
import com.hmk.challenge.bank.business.port.in.CreateAccountUseCase;
import com.hmk.challenge.bank.business.port.out.AccountDataProvider;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Named
@RequiredArgsConstructor
class CreateAccountUseCaseImpl implements CreateAccountUseCase {
    private final AccountDataProvider accountDataProvider;

    @Override
    public Account execute(Account account) {
        account.setId(UUID.randomUUID());
        account.selfValidation();

        return accountDataProvider.save(account);
    }
}
