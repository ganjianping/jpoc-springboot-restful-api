package com.ganjp.jpoc.module.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganjp.jpoc.module.auth.user.UserController;
import com.ganjp.jpoc.module.auth.user.UserEntity;
import com.ganjp.jpoc.module.auth.user.UserRequest;
import com.ganjp.jpoc.module.auth.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserRequest validUserRequest;
    private UserEntity validUserEntity;
    private String validUserId;

    @BeforeEach
    public void setUp() {
        validUserRequest = new UserRequest();
        validUserRequest.setUserName("jianping");
        validUserRequest.setPassword("abcEFG@123");

        validUserEntity = new UserEntity();
    }

    @Test
    public void createUser_validRequest_returnsCreated() throws Exception {
        when(userService.createUser(any(UserRequest.class))).thenReturn(validUserEntity);

        mockMvc.perform(post("/auth/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(validUserRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(100101));

        verify(userService, times(1)).createUser(any(UserRequest.class));
    }

//    @Test
//    public void getUserById_userExists_returnsOk() throws Exception {
//        when(userService.getUserById(eq(validUserId))).thenReturn(Optional.of(validUserEntity));
//
//        mockMvc.perform(get("/auth/users/{id}", validUserId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.id").value(validUserId));
//
//        verify(userService, times(1)).getUserById(eq(validUserId));
//    }
//
//    @Test
//    public void getUserById_userNotFound_returnsNotFound() throws Exception {
//        when(userService.getUserById(eq(validUserId))).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/auth/users/{id}", validUserId))
//                .andExpect(status().isNotFound());
//
//        verify(userService, times(1)).getUserById(eq(validUserId));
//    }
//
//    @Test
//    public void getUsers_usersExist_returnsOk() throws Exception {
//        List<UserEntity> userList = new ArrayList<>();
//        userList.add(validUserEntity);
//        Page<UserEntity> userPage = new PageImpl<>(userList);
//
//        when(userService.getUsers(any(Pageable.class))).thenReturn(userPage);
//
//        mockMvc.perform(get("/auth/users").param("page", "0").param("size", "10"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.content[0].id").value(validUserId));
//
//        verify(userService, times(1)).getUsers(any(Pageable.class));
//    }
//
//    @Test
//    public void getUsers_noUsers_returnsOk() throws Exception {
//        Page<UserEntity> emptyPage = Page.empty();
//
//        when(userService.getUsers(any(Pageable.class))).thenReturn(emptyPage);
//        mockMvc.perform(get("/auth/users").param("page", "0").param("size", "10"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.content").isEmpty());
//
//        verify(userService, times(1)).getUsers(any(Pageable.class));
//    }
//
//    @Test
//    public void updateUser_userExists_returnsOk() throws Exception {
//        when(userService.updateUser(eq(validUserId), any(UserRequest.class))).thenReturn(Optional.of(validUserEntity));
//
//        mockMvc.perform(put("/auth/users/{id}", validUserId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(validUserRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.id").value(validUserId));
//
//        verify(userService, times(1)).updateUser(eq(validUserId), any(UserRequest.class));
//    }
//
//    @Test
//    public void updateUser_userNotFound_returnsNotFound() throws Exception {
//        when(userService.updateUser(eq(validUserId), any(UserRequest.class))).thenReturn(Optional.empty());
//
//        mockMvc.perform(put("/auth/users/{id}", validUserId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(validUserRequest)))
//                .andExpect(status().isNotFound());
//
//        verify(userService, times(1)).updateUser(eq(validUserId), any(UserRequest.class));
//    }
//
//    @Test
//    public void deleteUser_userExists_returnsOk() throws Exception {
//        when(userService.deleteUser(eq(validUserId))).thenReturn(true);
//
//        mockMvc.perform(delete("/auth/users/{id}", validUserId))
//                .andExpect(status().isOk());
//
//        verify(userService, times(1)).deleteUser(eq(validUserId));
//    }
//
//    @Test
//    public void deleteUser_userNotFound_returnsNotFound() throws Exception {
//        when(userService.deleteUser(eq(validUserId))).thenReturn(false);
//
//        mockMvc.perform(delete("/auth/users/{id}", validUserId))
//                .andExpect(status().isNotFound());
//
//        verify(userService, times(1)).deleteUser(eq(validUserId));
//    }
}

