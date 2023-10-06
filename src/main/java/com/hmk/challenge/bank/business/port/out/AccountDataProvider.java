package com.hmk.challenge.bank.business.port.out;

import com.hmk.challenge.bank.business.domain.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountDataProvider {

    Account save(Account account);

    Optional<Account> findById(UUID accountId);

    Optional<Account> findByIdAndLockForWrite(UUID accountId);
}
