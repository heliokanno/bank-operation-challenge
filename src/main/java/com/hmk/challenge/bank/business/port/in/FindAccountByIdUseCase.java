package com.hmk.challenge.bank.business.port.in;

import com.hmk.challenge.bank.business.domain.Account;

import java.util.UUID;

public interface FindAccountByIdUseCase {

    Account execute(UUID accountId);
}
