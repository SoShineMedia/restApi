package com.soshinemedia.auth.repository;

import com.soshinemedia.auth.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
