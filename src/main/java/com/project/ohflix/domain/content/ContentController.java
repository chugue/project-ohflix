package com.project.ohflix.domain.content;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ContentController {
    private final ContentService contentService;
    
    // 최신 콘텐츠 페이지
    @GetMapping("/api/latest-content")
    public String getLatest(HttpServletRequest request) {
        List<ContentResponse.LatestContentDTO> respDTOList = contentService.findLatestContent();
        request.setAttribute("latestContentDTO", respDTOList);

        return "content/latest-content";
    }

    @GetMapping("/api/content-details")
    public String getContentDetails(HttpServletRequest request) { //TODO : 나중에 여기 PathVariable로 contentID 설정해야됨
        Integer contentId = 2;
        ContentResponse.DetailsDTO respDTO= contentService.getContentDetails(contentId);
        request.setAttribute("DetailsDTO", respDTO);
        return "content/content-details";
    }

    @GetMapping("/api/search")
    @ResponseBody
    public List<ContentResponse.SearchResultDTO> search(@RequestParam String query) {
        System.out.println("Search query: " + query); // 로그 출력
        List<ContentResponse.SearchResultDTO> results = contentService.searchContentsByTitle(query);
        System.out.println("Search results: " + results.size()); // 결과 로그 출력
        return results;
    }

}
