package com.Code.Inbound.Assignment_SDE_Intern.service;

import com.Code.Inbound.Assignment_SDE_Intern.UserDto;
import com.Code.Inbound.Assignment_SDE_Intern.dao.UserRepo;
import com.Code.Inbound.Assignment_SDE_Intern.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User addUser(UserDto user) {
        User userAdd =new User();
        userAdd.setUsername(user.getUsername());
        userAdd.setEmail(user.getEmail());
        userAdd.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(userAdd);
    }

    public User updateUser(Long id, UserDto user) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return userRepo.save(existingUser);
        }
        return null;
    }

    public User deleteUser(Long id) {
        User userToDelete = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepo.deleteById(id);
        return userToDelete;
    }
    
    public User getUser(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}
