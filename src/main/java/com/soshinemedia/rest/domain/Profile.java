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
    private long cellNum;
    @Getter @Setter
    private String qrCode;
    @Getter @Setter
    private String accountNumber;
    @Getter @Setter
    private float bankAccountNumber;
    @Getter @Setter
    private float balance;
    @Getter @Setter
    private float cash;
    @Getter @Setter
    private Timestamp createdAt;
    @LastModifiedDate
    private LocalDate lastModifiedDate;
    @Getter @Setter
    private Timestamp updatedAt;

    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile(String address, String format, String name, String uuid) {
        this.accountNumber = address;
        this.qrCode = format;
        this.name = name;
        this.uuid = uuid;
    }

    public void Profile(){

    }
}
