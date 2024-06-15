package com.project.ohflix.domain;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main-page")
    public String getMainPage(){
        return "main-page";
    }
    @GetMapping("/account")
    public String getAccountPage(){
        return "account";
    }
    @GetMapping("/login-form")
    public String getLoginForm(){
        return "login-form";
    }
    @GetMapping("/content-details")
    public String getContentDetails(){
        return "content-details";
    }
    @GetMapping("/paymethod-register-form")
    public String getPaymethodRegisterForm(){
        return "paymethod-register-form";
    }
}
