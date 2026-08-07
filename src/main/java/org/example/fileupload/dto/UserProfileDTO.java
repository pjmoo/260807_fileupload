package org.example.fileupload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UserProfileDTO(
        @NotBlank String name,
        @NotNull MultipartFile image
) {
}
