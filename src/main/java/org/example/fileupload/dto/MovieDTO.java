package org.example.fileupload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record MovieDTO(
        @NotBlank String title,
        @NotEmpty List<MultipartFile> images) {
}
