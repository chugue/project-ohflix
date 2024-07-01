package com.project.ohflix.domain.content;

import com.project.ohflix.domain.mylist.MyList;
import com.project.ohflix.domain.mylist.MyListService;
import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ContentController {
    private final ContentService contentService;
    private final MyListService myListService;
    private final HttpSession httpSession;

    @GetMapping("/api/latest-content")
    public String getLatest(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("sessionUser");
        ContentResponse.LatestContentDTO respDTOList = contentService.findLatestContent(sessionUser.getId());
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

    //시청한 영화 시간 저장하기
    @PostMapping("/api/video/save-time")
    public ResponseEntity<?> savePlayedTime(@RequestBody ContentRequest.VideoProgressDTO videoProgressDTO) {
        System.out.println("videoProgressDTO = " + videoProgressDTO);
        System.out.println("Saved time: " + videoProgressDTO.getCurrentTime());  // 저장된 시간을 콘솔에 출력
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("sessionUser");
        myListService.savePlayedTime(videoProgressDTO, sessionUser.getId());
        return ResponseEntity.ok().build();
    }

    //시청한 영화 시간 가져오기
    @GetMapping("/api/video/current-time")
    public ResponseEntity<?> getVideoCurrentTime(@RequestParam("filename") String filename) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("sessionUser");
        Double playedTime=myListService.getPlayedTime(sessionUser.getId(), filename);
        System.out.println("Returning current time: " + playedTime);  // 반환할 시간을 콘솔에 출력
        return ResponseEntity.ok().body(new ContentRequest.VideoProgressDTO(filename, playedTime));
    }

}
