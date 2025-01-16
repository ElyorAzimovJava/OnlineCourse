package org.example.onlinecourse.controller.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.onlinecourse.dto.authDto.LoginResponseDto;
import org.example.onlinecourse.dto.authDto.LoginRequestDto;
import org.example.onlinecourse.dto.authDto.RegisterUserDto;
import org.example.onlinecourse.dto.authDto.TokenDto;
import org.example.onlinecourse.utils.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AuthController.BASE_PATH)
public interface AuthController {

    String BASE_PATH = RestConstants.BASE_PATH_V1 + "/auth";
    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<String> register(@RequestBody @Valid RegisterUserDto user, HttpServletRequest request);
    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request);
    @PostMapping("refresh-token")
    ResponseEntity<TokenDto> refreshToken(@RequestParam String token);


}
