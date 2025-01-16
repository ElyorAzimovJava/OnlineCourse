package org.example.onlinecourse.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.onlinecourse.dto.authDto.LoginResponseDto;
import org.example.onlinecourse.dto.authDto.LoginRequestDto;
import org.example.onlinecourse.dto.authDto.RegisterUserDto;
import org.example.onlinecourse.dto.authDto.TokenDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> register(RegisterUserDto registerDto, HttpServletRequest request);
    ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto, HttpServletRequest request);
     ResponseEntity<TokenDto> refreshToken(String refreshToken);

}
