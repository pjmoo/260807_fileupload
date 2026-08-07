package org.example.fileupload.controller;

import lombok.RequiredArgsConstructor;
import org.example.fileupload.dto.UserProfileDTO;
import org.example.fileupload.service.UserProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping
    public String profiles() {
        return "profiles/list";
    }

    @PostMapping
    public String uploadProfile(
            @Validated UserProfileDTO userProfileDTO) {
        userProfileService.insert(userProfileDTO);
        return "redirect:/profiles";
    }
}
