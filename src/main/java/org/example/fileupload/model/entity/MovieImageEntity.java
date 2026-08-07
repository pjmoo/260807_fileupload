package org.example.fileupload.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "movie_images")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MovieImageEntity extends BaseEntity {
    private String storedFileName;
    private String originalFileName;
    private int sortOrder;

    public String url() {
        return "/upload/%s".formatted(storedFileName);
//        return storedFileName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @Setter
    private MovieEntity movie;
}
