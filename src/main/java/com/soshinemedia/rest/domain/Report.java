package com.soshinemedia.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reports")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report extends AbstractAuditableEntity<User, Long> implements Serializable {

    @Column
    private String title;
    @Column
    private String post;

    @ManyToOne
    @JoinColumn(name="case_id")
    private Case courtcase;
}
