package com.sanjay.auth.repository;

import com.sanjay.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.role where u.email=:email")
    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);
}
