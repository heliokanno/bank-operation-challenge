package com.hmk.challenge.bank.business.port.in;

import com.hmk.challenge.bank.business.domain.Account;

public interface CreateAccountUseCase {

    Account execute(Account account);
}
