package org.example.fileupload.dto;

public record UploadFile(String originalName, String storedName) {
    public String url() {
        return "/upload/%s".formatted(storedName);
    }
}
