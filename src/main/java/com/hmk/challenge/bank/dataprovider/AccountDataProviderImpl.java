package com.hmk.challenge.bank.dataprovider;

import com.hmk.challenge.bank.business.domain.Account;
import com.hmk.challenge.bank.business.port.out.AccountDataProvider;
import com.hmk.challenge.bank.dataprovider.converter.AccountConverter;
import com.hmk.challenge.bank.dataprovider.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class AccountDataProviderImpl implements AccountDataProvider {

    private final AccountRepository repository;

    @Override
    public Account save(Account account) {
        var accountEntity = AccountConverter.from(account);
        return AccountConverter.to(repository.save(accountEntity));
    }

    @Override
    public Optional<Account> findById(UUID accountId) {
        var accountEntity = repository.findById(accountId);
        return accountEntity.map(AccountConverter::to);
    }

    @Override
    public Optional<Account> findByIdAndLockForWrite(UUID accountId) {
        var accountEntity = repository.findByIdAndLockForWrite(accountId);
        return accountEntity.map(AccountConverter::to);
    }

}
