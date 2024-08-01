package com.email_service.demo.api.controller.openApi;

import com.email_service.demo.domain.model.EmailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Email", description = "Controlador da Email")
public interface EmailControllerDocumentation {

    @Operation(summary = "Recebe as propiedades para enviar o Email",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "500", description = "Erro ao montar o email",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
    public ResponseEntity<Void> sendEmail( EmailDTO emailDTO,  MultipartFile file, HttpServletRequest request );
}
