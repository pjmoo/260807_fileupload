package org.example.fileupload.util;

import org.example.fileupload.dto.UploadFile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Component
public class FileStore {
    private final Path uploadPath = Path.of("src/main/resources/static/upload");
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "webp");

    public UploadFile storeFile(MultipartFile image) {
        // 1. 비어 있는지 검증
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어 있습니다.");
        }
        String originalName = Objects.requireNonNull(image.getOriginalFilename());
        // 1.1. 안전한 파일명 처리
        String safeName = StringUtils.cleanPath(originalName);
        // 1.2. 확장자
        String extension = StringUtils.getFilenameExtension(safeName); // 확장자만 추출 (*.ext)
        if (extension == null || !ALLOWED_EXTENSIONS.contains(
                extension.toLowerCase(Locale.ROOT))) {
            throw new IllegalArgumentException("허용되지 않는 파일 형식입니다. %s".formatted(extension));
        }
        // 1.3. 파일 이름 변경 (UUID)
        String storedName = UUID.randomUUID() + "." + extension.toLowerCase(Locale.ROOT);

        // 2. 디렉터리 준비
        try {
            Files.createDirectories(uploadPath);
//            Path filePath = uploadPath.resolve(safeName).normalize(); // 추가적인 파일명, 경로 방어
            Path filePath = uploadPath.resolve(storedName).normalize(); // 추가적인 파일명, 경로 방어
            image.transferTo(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        return new UploadFile(originalName, safeName);
        return new UploadFile(originalName, storedName);
    }
}
