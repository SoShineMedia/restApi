package com.soshinemedia.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "firms")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Firm extends AbstractAuditableEntity<User, Long> implements Serializable {

    @Column
    private String name;

    //app info
    @Column
    private String creator;

    //business info
    @Column
    private String company_number;
    private String registered_address;

    //contacts
    @Column
    private String address;
    private String email;
    private String telephone;
    private String website;
    //private Link[] social;

    @OneToMany(mappedBy = "firm")
    private List<Case> cases;

}