package com.hmk.challenge.bank.business.domain.exception;

public class UnauthorizedTransactionException extends RuntimeException {
    public UnauthorizedTransactionException() {
        super("Unauthorized transaction.");
    }
}
