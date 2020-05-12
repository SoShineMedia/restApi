package com.soshinemedia.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "files")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File extends AbstractAuditableEntity<User, Long> implements Serializable {

    @Column
    private String name;
    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name="document_id")
    private Document document;

}
