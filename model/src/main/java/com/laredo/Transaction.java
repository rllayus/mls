package com.laredo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "TRANSACTION")
public class Transaction extends AuditableEntity{
    @Id
    @UuidGenerator
    private String id;
    @Column(name = "ORIGIN_ACCOUNT", length = 20, nullable = false)
    private String originAccount;
    @Column(name = "ORIGIN_NAME", length = 160, nullable = false)
    private String originName;
    @Column(name = "DESTINATION_ACCOUNT", length = 20, nullable = false)
    private String destinationAccount;
    @Column(name = "DESTINATION_NAME", length = 160, nullable = false)
    private String destinationName;

    @Column(name = "CODE_BANK_DESTINATION", length = 5, nullable = false)
    private short codeBankDestination;
    @Column(name = "CODE_BANK_ORIGIN", length = 5, nullable = false)
    private short codeBankOrigin;

    @Column(name = "AMOUNT", precision = 20, scale = 2)
    private BigDecimal amount;
    @Column(name = "DESCRIPTION", length = 250, nullable = true)
    private String description;

}
