package com.project.ohflix.domain.mylist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ohflix._core.utils.ApiUtil;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.user.SessionUser;
import com.project.ohflix.domain.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Controller
@EnableAsync
public class MyListController {
    private final HttpSession session;
    private final MyListService myListService;
    private final UserService userService;
    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private final ObjectMapper objectMapper;



//    @PostMapping("/api/my-favorite-list")
//    @ResponseBody
//    public ResponseEntity<List<MyListResponse.ContentDTO>> sendRequestToOpenAI(@RequestBody MyListRequest.OpenAIRequest openAIRequest) {
//        return myListService.processOpenAIRequest(openAIRequest);
//    }

    @GetMapping("/api/my-favorite-list")
    public String getMyFavList(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        Integer userId = null;
        if (sessionAdmin != null) {
            userId = sessionAdmin.getId();
        }
        if (sessionUser != null) {
            userId = sessionUser.getId();
        }
        MyListResponse.MyListDTO respDTO = myListService.findMyListById(userId);

        request.setAttribute("MyListDTO", respDTO);

        return "mylist/my-favorite-list";
    }

    @GetMapping("/api/my-favorite-list/openai")
    @ResponseBody
    public CompletableFuture<List<Content>> getOpenAIRecommendations() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        Integer userId = null;
        if (sessionAdmin != null) {
            userId = sessionAdmin.getId();
        }
        if (sessionUser != null) {
            userId = sessionUser.getId();
        }
        return myListService.getOpenAi(userId);
    }
    // 찜하기

    @PostMapping("/api/users/{contentId}/favorite")
    public ResponseEntity<?> addFavorite(@PathVariable int contentId, MyListRequest.AddFavoriteDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        Integer userId = null;
        if (sessionAdmin != null) {
            userId = sessionAdmin.getId();
        }
        if (sessionUser != null) {
            userId = sessionUser.getId();
        }
        reqDTO.setUserId(userId);
        reqDTO.setContentId(contentId);

        myListService.addFavorite(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(true));  // 찜 성공 여부를 JSON으로 반환
    }

    // 찜하기 취소
    @PostMapping("/api/users/{contentId}/unfavorite")
    public ResponseEntity<?> removeFavorite(@PathVariable int contentId, MyListRequest.RemoveFavoriteDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        Integer userId = null;
        if (sessionAdmin != null) {
            userId = sessionAdmin.getId();
        }
        if (sessionUser != null) {
            userId = sessionUser.getId();
        }
        reqDTO.setUserId(userId);
        reqDTO.setContentId(contentId);

        myListService.removeFavorite(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(true));  // 찜 취소 성공 여부를 JSON으로 반환
    }
}
