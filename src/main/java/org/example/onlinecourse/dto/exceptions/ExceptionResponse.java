
package org.example.onlinecourse.dto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {
    private String message;
    private Integer statusCode;
    private String url;
    private LocalDateTime time;
}
