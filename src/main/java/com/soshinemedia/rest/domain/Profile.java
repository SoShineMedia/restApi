package com.soshinemedia.rest.domain;

//import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String sec_hash;
    private long cell_num;
    private String qr_code;
    private Timestamp dob;
    private Timestamp created_at;
    private Timestamp updated_at;

    private float balance;

    @OneToOne
    private User user;
}
