package com.hmk.challenge.bank.web.response;

import com.hmk.challenge.bank.business.domain.Transaction;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@ToString
@Builder
public class TransactionResponse {
    private UUID id;
    private UUID sourceAccountId;
    private UUID targetAccountId;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public static TransactionResponse from(final Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .sourceAccountId(transaction.getTargetAccountId())
                .targetAccountId(transaction.getTargetAccountId())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}