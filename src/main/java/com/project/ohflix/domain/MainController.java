package com.project.ohflix.domain;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage(){
        return "main-page";
    }
    @GetMapping("/1")
    public String getAccountPage(){
        return "account-view";
    }
    @GetMapping("/2")
    public String getLoginForm(){
        return "login-form";
    }
    @GetMapping("/3")
    public String getContentDetails(){
        return "content-details";
    }
    @GetMapping("/4")
    public String getPaymethodRegisterForm(){
        return "paymethod-form";
    }
    @GetMapping("/5")
    public String getProfileView(){
        return "profile-form";
    }
}
