package com.soshinemedia.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill extends AbstractAuditableEntity<User, Long> implements Serializable {
    @Column
    private String name;
    @Column
    private Float balance;
    @Column
    private Float total;
    @Column
    private Float installmentAmount;
    @Column
    private String installmentInterval;
    @Column
    private String status;
    @Column
    private LocalDate dueDate;
    @Column
    private Float interest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    private Case courtcase;
}
