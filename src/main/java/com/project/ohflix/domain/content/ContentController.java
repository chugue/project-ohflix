package com.project.ohflix.domain.content;

import jakarta.servlet.http.HttpServletRequest;
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
        List<ContentResponse.LatestContentDTO> respDTOList = contentService.findLatestContent();
        request.setAttribute("latestContentList", respDTOList);

        return "content/latest-content";
    }

    @GetMapping("/api/content-details")
    public String getContentDetails() {
        return "content/content-details";
    }


}
