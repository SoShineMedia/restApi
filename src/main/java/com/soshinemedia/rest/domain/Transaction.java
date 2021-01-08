package com.soshinemedia.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private TransactionType type;
    private String description;

    private Timestamp created_at;

    @ManyToOne
    @JoinColumn(name="from_id")
    private Profile from;

    @ManyToOne
    @JoinColumn(name="to_id")
    private Profile to;
}
