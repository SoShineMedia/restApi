package com.soshinemedia.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="user_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private LogType type;
    private String description;

    private Timestamp created_at;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}