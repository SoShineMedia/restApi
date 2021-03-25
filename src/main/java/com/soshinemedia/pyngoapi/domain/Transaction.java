package com.soshinemedia.pyngoapi.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private TransactionStatus status;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private BigDecimal amount;
    @Getter @Setter
    private BigDecimal value;
    @Getter @Setter
    private Timestamp createdAt;

    @Getter @Setter
    private String fromAddress;

    @Getter @Setter
    private String toAddress;


}
