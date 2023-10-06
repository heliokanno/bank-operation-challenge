package com.hmk.challenge.bank.dataprovider.converter;

import com.hmk.challenge.bank.business.domain.Transaction;
import com.hmk.challenge.bank.dataprovider.entity.AccountEntity;
import com.hmk.challenge.bank.dataprovider.entity.TransactionEntity;

public class TransactionConverter {

    public static Transaction to(TransactionEntity transactionEntity) {
        return Transaction.builder()
                .id(transactionEntity.getId())
                .sourceAccountId(transactionEntity.getSourceAccount().getId())
                .targetAccountId(transactionEntity.getTargetAccount().getId())
                .amount(transactionEntity.getAmount())
                .createdAt(transactionEntity.getCreatedAt())
                .build();
    }

    public static TransactionEntity from(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .sourceAccount(AccountEntity.builder()
                        .id(transaction.getSourceAccountId())
                        .build())
                .targetAccount(AccountEntity.builder()
                        .id(transaction.getTargetAccountId())
                        .build())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .build();
    }


}