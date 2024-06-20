package com.project.ohflix.domain;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage() {
        return "main-page";
    }
    @GetMapping("/outer-page")
    public String getOuterPage() {
        return "outer-page";
    }
}
