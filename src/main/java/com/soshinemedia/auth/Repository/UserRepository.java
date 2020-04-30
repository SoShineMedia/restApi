package com.soshinemedia.auth.Repository;

import com.soshinemedia.auth.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
