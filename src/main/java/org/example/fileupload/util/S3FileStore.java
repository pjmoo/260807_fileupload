package org.example.fileupload.util;

import lombok.RequiredArgsConstructor;
import org.example.fileupload.config.StorageProperty;
import org.example.fileupload.dto.UploadFile;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Primary
@Component
@RequiredArgsConstructor
public class S3FileStore implements FileStore {
    private final S3Client s3Client;
    private final StorageProperty storageProperty;

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "webp");

    @Override
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
        // S3 -> 파일 호출을 위한 key
        String key = UUID.randomUUID() + "." + extension.toLowerCase(Locale.ROOT);

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(storageProperty.bucket())
                .key(key)
                .build();

        try (InputStream inputStream = image.getInputStream()) {
            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, image.getSize()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new UploadFile(originalName, key);
    }
}
