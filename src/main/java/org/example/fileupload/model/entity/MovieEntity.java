package org.example.fileupload.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "movies")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity extends BaseEntity {
    private String title;

    @OneToMany(
            mappedBy = "movie", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<MovieImageEntity> images = new ArrayList<>();

    public void addImage(MovieImageEntity image) {
        images.add(image);
        image.setMovie(this);
    }
}
