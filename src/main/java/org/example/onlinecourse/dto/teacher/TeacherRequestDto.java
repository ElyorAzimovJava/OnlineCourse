package org.example.onlinecourse.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
