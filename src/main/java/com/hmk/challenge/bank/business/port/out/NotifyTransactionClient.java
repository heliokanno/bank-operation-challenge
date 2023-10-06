package com.hmk.challenge.bank.business.port.out;

import java.util.UUID;

public interface NotifyTransactionClient {

    void send(UUID accountId, String msg);
}
