package com.hmk.challenge.bank.dataprovider.repository;

import com.hmk.challenge.bank.dataprovider.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
}
