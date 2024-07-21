package com.email_service.demo.domain.service;

import com.email_service.demo.domain.exception.EmailException;
import com.email_service.demo.domain.model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private String timestamp = LocalDateTime.now().toString();
    @Override
    public void sendEmail(Email email, MultipartFile file)  {
        try {

        log.info("[{}] - [EmailServiceImpl] Executing sendEmail, building email for {}", timestamp, email.getEmail());
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email.getEmail());
        helper.setSubject("Relatorio Energ Geradores Angra.");
        var text = String.format("Obrigado(a) %s pela confiança em nosso serviço! Segue em anexo o relatorio do serviço.", email.getName());
        helper.setText(text);

        log.info("[{}] - [EmailServiceImpl] assembled email", timestamp);
        InputStreamSource source = new ByteArrayResource(file.getBytes());
        var filename = email.getOrder_id().toString() + "_relatorio.pdf";
        helper.addAttachment(filename, source, "application/pdf");

        log.info("[{}] - [EmailServiceImpl] PDF indexed to email", timestamp);
        mailSender.send(message);
        log.info("[{}] - [EmailServiceImpl] Email successfully sent", timestamp);

        }catch (IOException | MessagingException e){
            new EmailException(
                    String.format("Erro ao encaminhar o email. Email: %s , id ordem: %d",email.getEmail(),email.getOrder_id()));
        }
    }
}
