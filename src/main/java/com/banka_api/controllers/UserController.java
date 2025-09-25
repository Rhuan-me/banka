package com.banka_api.controllers;

import com.banka_api.dtos.RegisterUserRequestDto;
import com.banka_api.dtos.UserResponseDto;
import com.banka_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users") // Define a URL base para todos os endpoints deste controller
public class UserController {

    @Autowired // Injeta a dependência do nosso serviço
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody RegisterUserRequestDto requestDto) {
        UserResponseDto createdUser = userService.registerNewUser(requestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}