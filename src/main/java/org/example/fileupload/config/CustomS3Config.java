package org.example.fileupload.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomS3Config {
//    @Bean
//    public S3Client s3Client(StorageProperty p) {
//        return S3Client.builder()
//                .region(Region.of(p.region()))
//                .endpointOverride(URI.create(p.endpoint()))
//                .credentialsProvider(
//                        StaticCredentialsProvider.create(
//                                AwsBasicCredentials.create(
//                                        p.accessKey(), p.secretKey())))
//                .forcePathStyle(true)
//                .build();
//    }
}
