package org.example.fileupload.model.repository;

import org.example.fileupload.model.entity.MovieImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageJpaRepository extends JpaRepository<MovieImageEntity, Long> {
}
