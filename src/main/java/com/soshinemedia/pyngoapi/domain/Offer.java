package com.soshinemedia.pyngoapi.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name="offers")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String description;
    @Column
    private OfferType type;

    @Column
    private Timestamp created_at;
    @Column
    private Timestamp updated_at;

    @Column
    private Float Lat;
    @Column
    private Float Lng;
    @Column
    private Boolean pin;
    @Column
    private long duration;
    @Column
    private float reward;
    @Column
    private int takers;

    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile user;
}
