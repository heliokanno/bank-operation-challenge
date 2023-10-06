package com.hmk.challenge.bank.business.port.in;

import com.hmk.challenge.bank.business.domain.Transaction;

public interface SendTransferUseCase {

    Transaction execute(Transaction transaction);
}
