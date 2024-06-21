package com.project.ohflix.domain.content;

import com.project.ohflix.domain.profileIcon.ProfileIconResponse;
import com.project.ohflix.domain.profileIcon.ProfileIconService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/api/latest-content")
    public String getLatest(HttpServletRequest request) {
        List<ProfileIconResponse.ProfileIconListDTO> respDTOList = contentService.findLatestContent();
        request.setAttribute("latestContentList", respDTOList);

        return "content/latest-content";
    }

    @GetMapping("/api/content-details")
    public String getContentDetails() {
        return "content/content-details";
    }

    @GetMapping("/api/profile-form")
    public String getProfileView() {
        return "profile/profile-form";
    }
}
