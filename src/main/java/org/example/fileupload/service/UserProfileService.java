package org.example.fileupload.service;

import lombok.RequiredArgsConstructor;
import org.example.fileupload.dto.UploadFile;
import org.example.fileupload.dto.UserProfileDTO;
import org.example.fileupload.model.entity.UserProfileEntity;
import org.example.fileupload.model.repository.UserProfileJpaRepository;
import org.example.fileupload.util.FileStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserProfileService {
    private final UserProfileJpaRepository userProfileJpaRepository;
    private final FileStore fileStore;

    @Transactional
    public void insert(UserProfileDTO dto) {
        UploadFile uploadFile = fileStore.storeFile(dto.image());
        UserProfileEntity userProfileEntity = UserProfileEntity.builder()
                .name(dto.name())
                .originalFileName(uploadFile.originalName())
                .storedFileName(uploadFile.storedName())
                .build();
        userProfileJpaRepository.save(userProfileEntity);
    }
}
