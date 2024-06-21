package com.project.ohflix.domain;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/40")
    public String getViewed() {
        return "viewed";
    }


    @GetMapping("/api/account-refund")
    public String getAccountRefundPage() {
        return "account/account-refund";
    }

    @PostMapping("/refund")
    public String refund() {
        return null;
    }

}
