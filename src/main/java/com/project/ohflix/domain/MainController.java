package com.project.ohflix.domain;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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

    @GetMapping("/")
    public String getMainPage() {
        return "main-page";
    }

    @GetMapping("/1")
    public String getAccountPage() {
        return "account-view";
    }

    @GetMapping("/3")
    public String getContentDetails() {
        return "content-details";
    }

    @GetMapping("/5")
    public String getProfileView() {
        return "profile-form";
    }

    @GetMapping("/52")
    public String profileSetting() {
        return "profile-setting";
    }

}
