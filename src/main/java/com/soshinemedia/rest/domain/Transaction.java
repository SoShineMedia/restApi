package com.soshinemedia.rest.domain;

import lombok.*;

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
    @Getter
    private long id;

    @Getter @Setter
    private TransactionType type;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private float amount;
    @Getter @Setter
    private float value;
    @Getter @Setter
    private Timestamp created_at;

    @ManyToOne
    @JoinColumn(name="from_id")
    private Profile from;

    @ManyToOne
    @JoinColumn(name="to_id")
    private Profile to;
}
