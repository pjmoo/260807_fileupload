package org.example.fileupload.model.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "movie_images")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieImageEntity extends BaseEntity {
    private String storedFileName;
    private String originalFileName;
    private int sortOrder;

    public String url() {
        return "/upload/%s".formatted(storedFileName);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @Setter
    private MovieEntity movie;
}
