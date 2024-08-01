package com.email_service.demo.api.controller;

import com.email_service.demo.api.controller.openApi.EmailControllerDocumentation;
import com.email_service.demo.domain.model.Email;
import com.email_service.demo.domain.model.EmailDTO;
import com.email_service.demo.domain.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Log4j2
@RestController
@RequestMapping("api/email")
@RequiredArgsConstructor
public class EmailController implements EmailControllerDocumentation {

    private final EmailService emailService;

    private String timestamp = LocalDateTime.now().toString();
    @PostMapping
    public ResponseEntity<Void> sendEmail(@Valid EmailDTO emailDTO, @RequestParam("file") MultipartFile file, HttpServletRequest request ){

        log.info("[{}] - [EmailController] IP: {}, Request: POST, EndPoint: 'api/email'", timestamp, request.getRemoteAddr());
        var email = Email.builder()
                .email(emailDTO.getEmail())
                .name(emailDTO.getName())
                .order_id(emailDTO.getOrder_id())
                .build();

        emailService.sendEmail(email,file);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
