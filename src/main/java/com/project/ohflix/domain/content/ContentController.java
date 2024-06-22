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
    public String getContentDetails(HttpServletRequest request) { //TODO : 나중에 여기 PathVariable로 contentID 설정해야됨
        Integer contentId = 2;
        ContentResponse.DetailsDTO respDTO= contentService.getContetnDetails(contentId);
        request.setAttribute("DetailsDTO", respDTO);
        return "content/content-details";
    }


}
