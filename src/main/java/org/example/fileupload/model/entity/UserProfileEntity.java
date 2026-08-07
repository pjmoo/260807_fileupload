package org.example.fileupload.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user_profile")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileEntity extends BaseEntity {
    private String name;
    private String originalFileName;
    private String storedFileName;

    public String url() {
        return "/upload/%s".formatted(storedFileName);
    }
}
