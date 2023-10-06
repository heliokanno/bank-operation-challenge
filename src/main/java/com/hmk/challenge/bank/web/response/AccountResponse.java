package com.hmk.challenge.bank.web.response;

import com.hmk.challenge.bank.business.domain.Account;
import com.hmk.challenge.bank.business.domain.enumeration.DocumentType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@ToString
@Builder
public class AccountResponse {
    private UUID id;
    private String name;
    private String document;
    private DocumentType documentType;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AccountResponse from(final Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .document(account.getDocument())
                .documentType(account.getDocumentType())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }
}