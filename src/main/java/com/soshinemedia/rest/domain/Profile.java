package com.soshinemedia.rest.domain;

//import org.springframework.data.annotation.Id;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Component
@Table(name="Profiles")
//@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String uuid;
    @Getter @Setter
    private long cell_num;
    @Getter @Setter
    private String qr_code;
    @Getter @Setter
    private String account_number;
    @Getter @Setter
    private float bank_account_number;
    @Getter @Setter
    private float balance;
    @Getter @Setter
    private Timestamp created_at;
    @LastModifiedDate
    private LocalDate lastModifiedDate;
    @Getter @Setter
    private Timestamp updated_at;

    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile(String publicKey, String path, String name, String uuid) {
        this.account_number = publicKey;
        this.qr_code = path;
        this.name = name;
        this.uuid = uuid;
    }
}
