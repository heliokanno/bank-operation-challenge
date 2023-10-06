package com.hmk.challenge.bank.business.port.out;

import com.hmk.challenge.bank.business.domain.Transaction;

public interface TransactionDataProvider {
    Transaction save(Transaction transaction);
}
