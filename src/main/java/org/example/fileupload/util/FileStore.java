package org.example.fileupload.util;

import org.example.fileupload.dto.UploadFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileStore {
    UploadFile storeFile(MultipartFile image);
}
