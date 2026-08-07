package org.example.fileupload.controller;

import lombok.RequiredArgsConstructor;
import org.example.fileupload.config.StorageProperty;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

//@Controller
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileViewController {
    private final S3Client s3Client;
    private final StorageProperty storageProperty;

    @GetMapping("/{id}")
    // import org.springframework.core.io.Resource;
    public ResponseEntity<Resource> getFile(@PathVariable String id) {
        ResponseInputStream<GetObjectResponse> response =
                s3Client.getObject(b -> b
                        .bucket(storageProperty.bucket()).key(id));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(response.response().contentType()))
                .body(new InputStreamResource(response));
    }

}
