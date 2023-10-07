package com.hmk.challenge.bank.integration;

import com.hmk.challenge.bank.business.port.out.NotifyTransactionClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotifyTransactionClientImpl implements NotifyTransactionClient {
    @Override
    public void send(UUID accountId, String msg) {
        //TODO
    }
}
