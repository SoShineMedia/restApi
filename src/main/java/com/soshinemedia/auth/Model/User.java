package com.soshinemedia.auth.Model;


import lombok.Data;
//import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {
    private @Id @GeneratedValue Long id;

    User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

}
