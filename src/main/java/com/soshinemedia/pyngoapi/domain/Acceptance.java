package com.soshinemedia.pyngoapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name="acceptances")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Acceptance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;
    private Status status;

    private Timestamp created_at;
    private Timestamp updated_at;

    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile user;
    @ManyToOne
    @JoinColumn(name="offer_id")
    private Offer offer;
}

