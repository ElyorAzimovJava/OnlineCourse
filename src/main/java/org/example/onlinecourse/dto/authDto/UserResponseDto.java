package org.example.onlinecourse.dto.authDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.onlinecourse.enums.UserType;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder@NoArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String telegramUsername;
    private UserType userType;
}
