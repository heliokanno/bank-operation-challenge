package com.hmk.challenge.bank.business.domain;

import com.hmk.challenge.bank.business.domain.exception.ValidationException;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
public class Transaction {
    private UUID id;
    private UUID sourceAccountId;
    private UUID targetAccountId;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public void selfValidation() {
        if (Objects.isNull(sourceAccountId)) {
            throw new ValidationException("Source account cannot be null");
        }
        if (Objects.isNull(targetAccountId)) {
            throw new ValidationException("Target account cannot be null");
        }
        if (Objects.equals(sourceAccountId, targetAccountId)) {
            throw new ValidationException("Source account cannot be the same as Target account");
        }
        if (Objects.isNull(this.amount) || this.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Invalid amount");
        }
    }

}
