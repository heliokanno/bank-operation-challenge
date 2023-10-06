package com.hmk.challenge.bank.dataprovider.converter;

import com.hmk.challenge.bank.business.domain.Account;
import com.hmk.challenge.bank.dataprovider.entity.AccountEntity;

public class AccountConverter {

    public static Account to(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .name(accountEntity.getName())
                .document(accountEntity.getDocument())
                .documentType(accountEntity.getDocumentType())
                .balance(accountEntity.getBalance())
                .createdAt(accountEntity.getCreatedAt())
                .updatedAt(accountEntity.getUpdatedAt())
                .build();
    }

    public static AccountEntity from(Account account) {
        return AccountEntity.builder()
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