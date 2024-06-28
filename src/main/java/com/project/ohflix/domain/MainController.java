package com.project.ohflix.domain;


import com.project.ohflix.domain.content.ContentResponse;
import com.project.ohflix.domain.content.ContentService;
import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final ContentService contentService;
    private final HttpSession session;

    @GetMapping("/")
    public String getLoginForm() {
        return "user/login-form";
    }

    @GetMapping("/api/main-page")
    public String getMainPage(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ContentResponse.MainPageDTO respDTO =  contentService.getMainPageData(sessionUser.getId());



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


    @GetMapping("/err/400")
    public String getErrorPage() {
        return "err";
    }

    @GetMapping("/api/contentId/play")
    public String getMainPage(@PathVariable String contentId, HttpServletRequest request) {

        return "main-page";
    }

}
