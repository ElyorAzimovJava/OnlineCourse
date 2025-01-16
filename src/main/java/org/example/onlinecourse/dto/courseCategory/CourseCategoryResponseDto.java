package org.example.onlinecourse.dto.courseCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCategoryResponseDto {
    private UUID id;
    private String name;
}
