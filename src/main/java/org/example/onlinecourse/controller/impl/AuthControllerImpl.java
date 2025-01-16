package org.example.onlinecourse.controller.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.controller.interfaces.AuthController;
import org.example.onlinecourse.dto.authDto.LoginResponseDto;
import org.example.onlinecourse.dto.authDto.LoginRequestDto;
import org.example.onlinecourse.dto.authDto.RegisterUserDto;
import org.example.onlinecourse.dto.authDto.TokenDto;
import org.example.onlinecourse.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final UserServiceImpl userService;
    @Override
    public ResponseEntity<String> register(RegisterUserDto user,HttpServletRequest request) {
        return userService.register(user,request);
    }

    @Override
    public ResponseEntity<TokenDto> refreshToken(String token) {
        return userService.refreshToken(token);
    }

    @Override
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto, HttpServletRequest request) {
        return userService.login(loginRequestDto,request);
    }
}
