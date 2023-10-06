package com.hmk.challenge.bank.dataprovider.repository;

import com.hmk.challenge.bank.dataprovider.entity.AccountEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ac FROM AccountEntity ac WHERE ac.id = :accountId ")
    Optional<AccountEntity> findByIdAndLockForWrite(@Param("accountId") UUID accountId);

}
