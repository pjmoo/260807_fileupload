package org.example.fileupload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class CustomS3Config {
    @Bean
    public S3Client s3Client(StorageProperty p) {
        return S3Client.builder()
                .region(Region.of(p.region()))
                .endpointOverride(URI.create(p.endpoint()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        p.accessKey(), p.secretKey())))
                .forcePathStyle(true)
                .build();
    }
}
