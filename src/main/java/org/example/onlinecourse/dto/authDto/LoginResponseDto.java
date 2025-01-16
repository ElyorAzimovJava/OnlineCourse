package org.example.onlinecourse.dto.authDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.onlinecourse.enums.UserType;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {
    private UserResponseDto responseDto;
    private TokenDto tokendto;
}
