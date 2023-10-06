package com.hmk.challenge.bank.dataprovider.entity;

import com.hmk.challenge.bank.business.domain.enumeration.DocumentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    @Column(name = "account_id", nullable = false)
    private UUID id;

    @Column(name = "des_name", nullable = false)
    private String name;

    @Column(name = "des_document", unique = true, nullable = false)
    private String document;

    @Enumerated(EnumType.STRING)
    @Column(name = "ind_document_type", nullable = false)
    private DocumentType documentType;

    @Column(name = "num_balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "dat_created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "dat_updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
