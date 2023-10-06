package com.hmk.challenge.bank.business.usecase;

import com.hmk.challenge.bank.business.domain.Account;
import com.hmk.challenge.bank.business.domain.exception.NotFoundException;
import com.hmk.challenge.bank.business.port.in.FindAccountByIdUseCase;
import com.hmk.challenge.bank.business.port.out.AccountDataProvider;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Named
@RequiredArgsConstructor
class FindAccountByIdUseCaseImpl implements FindAccountByIdUseCase {
    private final AccountDataProvider accountDataProvider;

    @Override
    public Account execute(UUID accountId) {
        return accountDataProvider.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found."));
    }
}
