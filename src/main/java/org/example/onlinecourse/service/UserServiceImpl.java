package org.example.onlinecourse.service;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.dto.authDto.LoginResponseDto;
import org.example.onlinecourse.dto.authDto.LoginRequestDto;
import org.example.onlinecourse.dto.authDto.RegisterUserDto;
import org.example.onlinecourse.dto.authDto.TokenDto;
import org.example.onlinecourse.entities.Device;
import org.example.onlinecourse.entities.User;
import org.example.onlinecourse.exceptions.DataAlreadyExists;
import org.example.onlinecourse.exceptions.DataNotFoundException;
import org.example.onlinecourse.exceptions.LoginException;
import org.example.onlinecourse.jwt.JWTService;
import org.example.onlinecourse.mapper.AuthMapper;
import org.example.onlinecourse.repository.UserRepository;
import org.example.onlinecourse.service.verification.EmailVerificationServiceImpl;
import org.example.onlinecourse.utils.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final DeviceServiceImpl deviceService;
    private final EmailVerificationServiceImpl emailVerification;
    private final JWTService jwtService;
    @Override
    public ResponseEntity<String> register(RegisterUserDto registerDto, HttpServletRequest request) {
        if(userRepository.existsByEmailOrTelegramUsername(registerDto.getEmail(), registerDto.getTelegramUsername())){
            throw new DataAlreadyExists("Email or telegram username already exist");
        };
        if (deviceService.isIPExist(request)) {
            throw new DataAlreadyExists("Device IP already exist");
        }
        User user = authMapper.toUser(registerDto);
        Device device = deviceService.createDevice(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsBlocked(false);
        user.setDevice(device);
        user.setIsVerified(false);
        userRepository.save(user);
        emailVerification.sendVerificationCode(user.getEmail());
        return ResponseEntity.ok(Messages.SUCCESS_SAVE_MESSAGE);
    }

    @Override
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto, HttpServletRequest request) {
        String ipAddress = deviceService.extractClientIpAddress(request);
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new DataNotFoundException(Messages.INCORRECT_PASSWORD_OR_EMAIL));
        if(user.getIsBlocked()){
            throw new LoginException(Messages.BLOCKER_USER);
        }
        if(!user.getIsVerified()){
            throw new LoginException(Messages.NOT_VERIFIED);
        }
        if(!user.getDevice().getIpAddress().equals(ipAddress)){
            throw new DataAlreadyExists(Messages.ANOTHER_DEVICE);
        }
        if(!passwordEncoder.matches(loginRequestDto.getPassword(),user.getPassword()))
            throw new LoginException(Messages.INCORRECT_PASSWORD_OR_EMAIL);

        return new ResponseEntity<>(new LoginResponseDto(authMapper.toResponse(user), tokenDtoCreator(user)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TokenDto> refreshToken(String refreshToken) {
        try{
            jwtService.tokenIsExpired(refreshToken);
        }catch (ExpiredJwtException e){
            throw new LoginException(Messages.REFRESH_TOKEN_EXPIRED);
        }
        String username = jwtService.getUsernameInToken(refreshToken);
       return new ResponseEntity<>( tokenDtoCreator(userRepository.findByEmail(username)
                .orElseThrow(() -> new LoginException("EMAIL NOT FOUND IN TOKEN"))),HttpStatus.OK);

    }

    private TokenDto tokenDtoCreator(User user){
        return new TokenDto(
                jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user)
        );

    }

}
