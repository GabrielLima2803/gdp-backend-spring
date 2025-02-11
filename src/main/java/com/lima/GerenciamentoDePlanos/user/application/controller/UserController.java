package com.lima.GerenciamentoDePlanos.user.application.controller;

import com.lima.GerenciamentoDePlanos.user.application.dto.LoginRequest;
import com.lima.GerenciamentoDePlanos.user.application.dto.LoginResponse;
import com.lima.GerenciamentoDePlanos.user.application.dto.RegisterUserDTO;
import com.lima.GerenciamentoDePlanos.user.application.mappers.UserRegisterAppMapper;
import com.lima.GerenciamentoDePlanos.user.application.usecases.UserUseCase;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserUseCase userUseCase;
    private final UserRegisterAppMapper appMapper;

    public UserController(UserUseCase userUseCase, UserRegisterAppMapper appMapper) {
        this.userUseCase = userUseCase;
        this.appMapper = appMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDTO dto) {
        User user = appMapper.toDomain(dto);
        User userSaved = userUseCase.registerUser(user);
        return ResponseEntity.ok(userSaved);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userUseCase.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

}
