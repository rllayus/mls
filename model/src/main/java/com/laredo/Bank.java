package com.laredo;

import com.laredo.enums.BankStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BANK")
public class Bank  extends AuditableEntity{
    @Id
    @UuidGenerator
    private String id;
    @Column(name = "CODE", length = 5, nullable = false)
    private short code;
    @Column(name = "ABBREVIATION", length = 10, nullable = false)
    private String abbreviation;
    @Column(name = "NAME", length = 200, nullable = false)
    private String name;
    @Column(name = "STATUS", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private BankStatus status;

    @Column(name = "url", length = 200, nullable = true)
    private String url;
    @Column(name = "connect_timeout", nullable = true)
    private Integer connectTimeout;
    @Column(name = "read_timeout", nullable = true)
    private Integer readTimeout;
    @Column(name = "user_to_api", nullable = true)
    private String user;
    @Column(name = "password_to_api", nullable = true)
    private String passwordToApi;

}
