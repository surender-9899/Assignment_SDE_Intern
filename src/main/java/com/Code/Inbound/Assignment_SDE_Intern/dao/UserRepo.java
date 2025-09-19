package com.Code.Inbound.Assignment_SDE_Intern.dao;

import com.Code.Inbound.Assignment_SDE_Intern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
