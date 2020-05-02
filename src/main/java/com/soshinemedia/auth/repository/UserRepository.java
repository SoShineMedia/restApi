package com.soshinemedia.auth.repository;

import com.soshinemedia.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
