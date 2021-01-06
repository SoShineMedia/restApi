package com.soshinemedia.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "events")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event extends AbstractAuditableEntity<User, Long> implements Serializable {
    private String title;
    private String description;
    private Date date;
    private String locationPin;
    private String address;

    @ManyToOne
    @JoinColumn(name="case_id")
    private Case courtcase;
}
