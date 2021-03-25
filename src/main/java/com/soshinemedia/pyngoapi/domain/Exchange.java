package com.soshinemedia.pyngoapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name="exchanges")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private float amount;
    private float cash_val;

    private String description;
    private Timestamp created_at;

    @ManyToOne
    @JoinColumn(name="rate_id")
    private ExchangeRate rate;

    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile user;
}
