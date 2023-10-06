package com.hmk.challenge.bank.business.domain;

import com.hmk.challenge.bank.business.domain.enumeration.DocumentType;
import com.hmk.challenge.bank.business.domain.exception.InsufficientBalanceException;
import com.hmk.challenge.bank.business.domain.exception.ValidationException;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
public class Account {
    private UUID id;
    private String name;
    private String document;
    private DocumentType documentType;
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void selfValidation() {
        if (Objects.isNull(id)) {
            throw new ValidationException("ID cannot be null");
        }
        if (Objects.isNull(name)) {
            throw new ValidationException("Name cannot be null");
        }
        if (Objects.isNull(document)) {
            throw new ValidationException("Document cannot be null");
        }
        if (Objects.isNull(documentType)) {
            throw new ValidationException("Document type cannot be null");
        }
    }

    public void withdraw(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
        this.balance = this.balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

}
