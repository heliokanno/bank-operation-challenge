package com.hmk.challenge.bank.web.request;

import com.hmk.challenge.bank.business.domain.Account;
import com.hmk.challenge.bank.business.domain.enumeration.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Document is mandatory")
    private String document;

    @NotNull(message = "Document type is mandatory")
    private DocumentType documentType;

    public Account to() {
        return Account.builder()
                .name(name)
                .document(document)
                .documentType(documentType)
                .build();
    }

}
