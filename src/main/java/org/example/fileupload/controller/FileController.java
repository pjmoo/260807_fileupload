package org.example.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;

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
        if (image.isEmpty()) {
            ra.addFlashAttribute("msg", "파일을 넣어주세요.");
            return "redirect:/files/new";
        }
        // 파일 업로드
        Path uploadPath = Path.of("src/main/resources/static/upload"); // 연습용
        // 경로가 없으면 에러가 터짐
        try {
            Files.createDirectories(uploadPath); // 없으면 만들어줌
            // 파일 저장용 경로가 필요
            Path filePath = uploadPath.resolve(image.getOriginalFilename());
            image.transferTo(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e); // 접근권한 혹은 파일 시스템 오류
        }
        ra.addFlashAttribute("msg", "업로드 성공 (%s)".formatted(image.getOriginalFilename()));
        return "redirect:/files/new"; // PRG
    }
}
