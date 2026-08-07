package org.example.fileupload.util;

import org.example.fileupload.dto.UploadFile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Component
public class FileStore {
    private final Path uploadPath = Path.of("src/main/resources/static/upload");

    public UploadFile storeFile(MultipartFile image) {
        // 1. 비어 있는지 검증
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어 있습니다.");
        }
        String originalName = Objects.requireNonNull(image.getOriginalFilename());
        // 1.1. 안전한 파일명 처리
        String safeName = StringUtils.cleanPath(originalName);
        // 1.2. 확장자 (TO DO)

        // 2. 디렉터리 준비
        try {
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(safeName).normalize(); // 추가적인 파일명, 경로 방어
            image.transferTo(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new UploadFile(originalName, safeName);
    }
}
