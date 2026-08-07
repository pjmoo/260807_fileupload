package org.example.fileupload.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadPath = Path.of("src/main/resources/static/upload")
                .toAbsolutePath().normalize();
        registry.addResourceHandler("/upload/**")
                .addResourceLocations(uploadPath.toUri().toString());
    }
}
