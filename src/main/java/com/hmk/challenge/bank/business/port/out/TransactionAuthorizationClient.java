package com.hmk.challenge.bank.business.port.out;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionAuthorizationClient {

    boolean authorize(UUID accountId, BigDecimal amount);
}
