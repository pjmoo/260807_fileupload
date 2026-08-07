package org.example.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/files")
public class FileController {
    @GetMapping("/new")
    public String filesForm() {
        return "files/form";
    }

    @PostMapping("/new")
    public String uploadFile(
            @RequestParam MultipartFile image,
            RedirectAttributes ra) {
        System.out.println("image = " + image);
        return "redirect:/files/new"; // PRG
    }
}
