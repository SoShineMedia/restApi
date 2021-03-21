package com.soshinemedia.rest.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
@Table(name="exchange_rates")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter @Setter
    private BigDecimal value;
    @Getter @Setter
    private Timestamp createdAt;

}
