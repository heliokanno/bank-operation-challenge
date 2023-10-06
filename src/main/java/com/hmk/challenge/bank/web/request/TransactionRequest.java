package com.hmk.challenge.bank.web.request;

import com.hmk.challenge.bank.business.domain.Transaction;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransactionRequest {

    @NotNull(message = "Source Account ID is mandatory")
    private UUID sourceAccountId;

    @NotNull(message = "Target Account ID is mandatory")
    private UUID targetAccountId;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.01", inclusive = false)
    @Digits(integer = 14, fraction = 2)
    private BigDecimal amount;

    public Transaction to() {
        return Transaction.builder()
                .sourceAccountId(sourceAccountId)
                .targetAccountId(targetAccountId)
                .amount(amount)
                .build();
    }

}
