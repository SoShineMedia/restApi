package com.soshinemedia.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cases")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Case extends AbstractAuditableEntity<User, Long> implements Serializable {
    //meta
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    private String status;

    //client
    @Column
    private String clientName;
    @Column
    private String clientTelephone;
    @Column
    private String clientCellphone;
    @Column
    private String clientEmail;

    @ManyToOne
    @JoinColumn(name="firm_id")
    private Firm firm;

    @OneToMany(mappedBy = "courtcase")
    private List<Document> documents;

    @OneToMany(mappedBy = "courtcase")
    private List<Event> events;

    @OneToMany(mappedBy = "courtcase")
    private List<Report> reports;

    @OneToOne(mappedBy = "courtcase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Bill bill;
}
