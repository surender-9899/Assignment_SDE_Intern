package com.Code.Inbound.Assignment_SDE_Intern.controller;

import com.Code.Inbound.Assignment_SDE_Intern.UserDto;
import com.Code.Inbound.Assignment_SDE_Intern.model.User;
import com.Code.Inbound.Assignment_SDE_Intern.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    // ✅ Mock a user with role "ADMIN" to bypass authentication
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetAllUsers() throws Exception {
        User user = new User();
        user.setId(10L);
        user.setUsername("surenderTest");
        user.setEmail("surenderTest@gmail.com");
        user.setPassword("123");

        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("surenderTest"))
                .andExpect(jsonPath("$[0].email").value("surenderTest@gmail.com"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreateUser() throws Exception {
        User user = new User();
        user.setId(10L);
        user.setEmail("surenderTest2@gmail.com");
        user.setUsername("surenderTest2");
        user.setPassword("encodedPassword");

        when(userService.addUser(any(UserDto.class))).thenReturn(user);

        UserDto dto = new UserDto("surenderTest2", "surenderTest2@gmail.com", "secret123");
        String userJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/users")
                        .with(csrf()) // ✅ Add CSRF token for POST requests
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("surenderTest2"))
                .andExpect(jsonPath("$.email").value("surenderTest2@gmail.com"));
    }
}
