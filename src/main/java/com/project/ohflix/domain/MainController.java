package com.project.ohflix.domain;


import com.project.ohflix.domain.content.ContentResponse;
import com.project.ohflix.domain.content.ContentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@RequiredArgsConstructor
@Controller
public class MainController {
    private final ContentService contentService;

    @GetMapping("/")
    public String getLoginForm() {
        return "user/login-form";
    }

    @GetMapping("/api/main-page")
    public String getMainPage(HttpServletRequest request) {
        ContentResponse.MainPageDTO respDTO =  contentService.getMainPageData();
        request.setAttribute("MainPageDTO", respDTO);
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
