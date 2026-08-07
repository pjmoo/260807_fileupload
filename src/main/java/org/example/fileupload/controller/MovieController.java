package org.example.fileupload.controller;

import lombok.RequiredArgsConstructor;
import org.example.fileupload.config.StorageProperty;
import org.example.fileupload.dto.MovieDTO;
import org.example.fileupload.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final StorageProperty storageProperty;

    @GetMapping
    public String movies(Model model) {
        model.addAttribute("form", new MovieDTO("", null));
        model.addAttribute("list", movieService.findAll());
        model.addAttribute("imagePath", "%s/%s".formatted(
                storageProperty.endpoint().replace("/s3", "/object/public"),
                storageProperty.bucket()));
        return "movies/list";
    }

    @PostMapping
    public String uploadMovie(
            @ModelAttribute("form") @Validated MovieDTO dto) {
        movieService.insert(dto);
        return "redirect:/movies";
    }
}
