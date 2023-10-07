package com.hmk.challenge.bank.integration;

import java.math.BigDecimal;
import java.util.UUID;

public record AuthorizationRequest(UUID accountId, BigDecimal amount) {
}
