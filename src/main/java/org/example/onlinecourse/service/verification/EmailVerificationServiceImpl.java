package org.example.onlinecourse.service.verification;

import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.dto.verification.VerificationDto;
import org.example.onlinecourse.entities.baseEntity.Verification;
import org.example.onlinecourse.exceptions.DataNotFoundException;
import org.example.onlinecourse.exceptions.LoginException;
import org.example.onlinecourse.repository.VerificationRepository;
import org.example.onlinecourse.utils.Messages;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImpl implements EmailVerificationService {
    private final JavaMailSenderImpl mailSender;
    private final VerificationRepository verificationRepository;

    @Override
    public void sendMessage(VerificationDto verificationDto) {

    }

    @Override
    public Boolean verify(VerificationDto verificationDto) {
        Verification byEmail = verificationRepository.findByEmail(verificationDto.getEmail());
        if(byEmail == null) {
            throw new DataNotFoundException(Messages.NOT_FOUND.formatted("Email"));
        }
        if(!byEmail.getMessage().equals(verificationDto.getMessage())){
            throw new LoginException(Messages.INCORRECT_VERIFICATION_CODE);
        }
        return true;
    }

    @Override
    public void sendVerificationCode(String email) {
        String password = generatePassword();
        mailMessage(email, Messages.VERIFICATION_SUBJECT, password);

        Verification verification = verificationRepository.findByEmail(email);

        if (verification == null) {
            verificationRepository.save(new Verification(email, password));
            return;
        }
        verification.setMessage(password);
        verificationRepository.save(verification);

    }

    private String generatePassword() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(0, 10));
        }
        return stringBuilder.toString();
    }

    private void mailMessage(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
            mailSender.send(simpleMailMessage);
    }
}