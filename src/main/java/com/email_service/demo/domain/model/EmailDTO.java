package com.email_service.demo.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class EmailDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private Long order_id;
}
