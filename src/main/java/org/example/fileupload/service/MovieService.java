package org.example.fileupload.service;

import lombok.RequiredArgsConstructor;
import org.example.fileupload.dto.MovieDTO;
import org.example.fileupload.dto.UploadFile;
import org.example.fileupload.model.entity.MovieEntity;
import org.example.fileupload.model.entity.MovieImageEntity;
import org.example.fileupload.model.repository.MovieImageJpaRepository;
import org.example.fileupload.model.repository.MovieJpaRepository;
import org.example.fileupload.util.FileStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    private final MovieJpaRepository movieJpaRepository;
    private final MovieImageJpaRepository movieImageJpaRepository;
    private final FileStore fileStore;

    public List<MovieEntity> findAll() {
        return movieJpaRepository.findAll();
    }

    @Transactional
    public void insert(MovieDTO dto) {
        MovieEntity movieEntity = MovieEntity.builder()
                .title(dto.title())
                .images(new ArrayList<>())
                .build();
        for (int i = 0; i < dto.images().size(); i++) {
            MultipartFile file = dto.images().get(i);
            if (file.isEmpty()) {
                continue;
            }
            UploadFile uploadFile = fileStore.storeFile(file);
            MovieImageEntity movieImageEntity = MovieImageEntity.builder()
                    .movie(movieEntity)
                    .originalFileName(uploadFile.originalName())
                    .storedFileName(uploadFile.storedName())
                    .build();
            movieEntity.addImage(movieImageEntity);
            movieImageJpaRepository.save(movieImageEntity);
        }

        movieJpaRepository.save(movieEntity);
    }
}
