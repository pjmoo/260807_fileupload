package org.example.fileupload.model.repository;

import org.example.fileupload.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {
    @EntityGraph(attributePaths = "images")
        // N+1 해결
    List<MovieEntity> findAll();
}
