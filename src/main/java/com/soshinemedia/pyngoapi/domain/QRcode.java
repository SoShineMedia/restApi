package com.soshinemedia.pyngoapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name="qr_codes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QRcode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LogType type;
    private String description;

    private Timestamp created_at;

    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile profile;

}
