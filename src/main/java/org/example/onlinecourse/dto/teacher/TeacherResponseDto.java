package org.example.onlinecourse.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
