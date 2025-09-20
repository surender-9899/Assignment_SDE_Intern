package com.Code.Inbound.Assignment_SDE_Intern.dao;

import com.Code.Inbound.Assignment_SDE_Intern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findAllById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
