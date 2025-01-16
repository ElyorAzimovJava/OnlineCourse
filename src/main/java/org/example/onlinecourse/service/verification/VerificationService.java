package org.example.onlinecourse.service.verification;

import org.example.onlinecourse.dto.verification.VerificationDto;

public interface VerificationService {
    void sendMessage(VerificationDto verificationDto);
    Boolean verify(VerificationDto verificationDto);
    void sendVerificationCode(String email);
}
