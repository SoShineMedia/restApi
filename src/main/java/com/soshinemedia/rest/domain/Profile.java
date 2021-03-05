package com.soshinemedia.rest.domain;

//import org.springframework.data.annotation.Id;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
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

    private String sec_hash;
    private long cell_num;
    private String qr_code;
    private String account_number;
    private Timestamp dob;
    private Timestamp created_at;
    private Timestamp updated_at;

    private float balance;

    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile(String publicKey, String path) {

        this.account_number = publicKey;
        this.qr_code = path;

    }
}
