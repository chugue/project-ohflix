package com.project.ohflix.domain;


import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getLoginForm() {
        return "user/login-form";
    }

    @GetMapping("/api/main-page")
    public String getMainPage(HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        System.out.println("👉👉👉👉👉" + sessionUser.getId());
        return "main-page";
    }
    @GetMapping("/outer-page")
    public String getOuterPage() {
        return "outer-page";
    }


    @GetMapping("/api/info-copy")
    public String getInfoCopyPage() {
        return "admin/info-copy";
    }


}
