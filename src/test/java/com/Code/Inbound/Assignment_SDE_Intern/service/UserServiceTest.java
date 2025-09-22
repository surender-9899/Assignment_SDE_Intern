package com.Code.Inbound.Assignment_SDE_Intern.service;

import com.Code.Inbound.Assignment_SDE_Intern.UserDto;
import com.Code.Inbound.Assignment_SDE_Intern.dao.UserRepo;
import com.Code.Inbound.Assignment_SDE_Intern.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void testAddUser() {
        // Given
        UserDto dto = new UserDto();
        dto.setUsername("surenderTest2");
        dto.setEmail("surenderTest2@gmail.com");
        dto.setPassword("password456");

        User user = new User();
        user.setId(10L);
        user.setUsername("surenderTest2");
        user.setEmail("surenderTest2@gmail.com");
        user.setPassword("encodedPassword");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepo.save(any(User.class))).thenReturn(user);

        // When
        User savedUser = userService.addUser(dto);

        // Then
        assertNotNull(savedUser, "Saved user should not be null");
        assertEquals("surenderTest2", savedUser.getUsername());
        assertEquals("surenderTest2@gmail.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
    }

    @Test
    void testGetUser_NotFound() {
        Long userId = 10L;
        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class,
                () -> userService.getUser(userId));

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testGetUser_Success() {
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);
        expectedUser.setUsername("testuser");
        expectedUser.setEmail("test@example.com");

        when(userRepo.findById(userId)).thenReturn(Optional.of(expectedUser));

        User foundUser = userService.getUser(userId);

        assertNotNull(foundUser, "User should not be null");
        assertEquals(userId, foundUser.getId());
        assertEquals("testuser", foundUser.getUsername());
    }
}
