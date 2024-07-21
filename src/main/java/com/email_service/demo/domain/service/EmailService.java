package com.email_service.demo.domain.service;

import com.email_service.demo.domain.model.Email;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

    void sendEmail(Email email, MultipartFile file);
}
