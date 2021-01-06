package com.soshinemedia.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "documents")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document extends AbstractAuditableEntity<User, Long> implements Serializable {
    private String name;
    private LocalDate dueDate;
    private String status;
    private String description;

    private String url;
    private String source;

    @OneToMany(mappedBy = "document")
    private List<File> files;

    @ManyToOne
    @JoinColumn(name="case_id")
    private Case courtcase;
}
