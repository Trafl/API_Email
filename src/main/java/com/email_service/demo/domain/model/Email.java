package com.email_service.demo.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class Email {
    private String name;
    private String email;
    private Long order_id;
}
