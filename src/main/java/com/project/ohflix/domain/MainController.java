package com.project.ohflix.domain;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage() {
        return "main-page";
    }

    @GetMapping("/11")
    public String getLoginForm() {
        return "login-form";
    }

    @GetMapping("/12")
    public String getPaymethodRegisterForm() {
        return "paymethod-form";
    }

    @GetMapping("/13")
    public String getPaymethodManage() {
        return "paymethod-manage";
    }

    @GetMapping("/21")
    public String getPasswordChangeForm() {
        return "password-change-form";
    }

    // account
    @GetMapping("/50")
    public String getAccountPage() {
        return "account-view";
    }

    // account
    @GetMapping("/51")
    public String accountMembership() {
        return "account-membership";
    }

    @GetMapping("/3")
    public String getContentDetails() {
        return "content-details";
    }

    @GetMapping("/5")
    public String getProfileView() {
        return "profile-form";
    }
}
