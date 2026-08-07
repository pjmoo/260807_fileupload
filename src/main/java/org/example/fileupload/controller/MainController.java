package org.example.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("/")
    public String index() {
//        return "index";
//        return "redirect:/files/new";
//        return "redirect:/profiles";
        return "redirect:/movies";
    }
}
