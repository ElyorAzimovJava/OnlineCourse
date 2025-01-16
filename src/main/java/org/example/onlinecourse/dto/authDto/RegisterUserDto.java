package org.example.onlinecourse.dto.authDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.onlinecourse.enums.UserType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterUserDto {
    @NotBlank(message = "First name must not be null")
    private String firstName;
    private String lastName;
    private String email;
    private String telegramUsername;
    private String password;
    private UserType userType;
}
