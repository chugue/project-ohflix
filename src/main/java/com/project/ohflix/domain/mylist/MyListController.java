package com.project.ohflix.domain.mylist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.ohflix._core.utils.ApiUtil;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.user.SessionUser;
import com.project.ohflix.domain.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Controller
public class MyListController {
    private final HttpSession session;
    private final MyListService myListService;
    private final UserService userService;
    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private final ObjectMapper objectMapper;


    @PostMapping("/api/my-favorite-list")
    @ResponseBody
    public ResponseEntity<List<MyListResponse.ContentDTO>> sendRequestToOpenAI(@RequestBody MyListRequest.OpenAIRequest openAIRequest) {
        return processOpenAIRequest(openAIRequest);
    }

    //restapi 처리할거
    public ResponseEntity<List<MyListResponse.ContentDTO>> processOpenAIRequest(MyListRequest.OpenAIRequest openAIRequest) {
        try {
            // 메시지 생성
            ObjectNode messageNode = objectMapper.createObjectNode();
            messageNode.put("role", "user");
            messageNode.put("content", openAIRequest.getMessage());

            // 요청 본문 생성
            ObjectNode requestBodyNode = objectMapper.createObjectNode();
            requestBodyNode.put("model", openAIRequest.getModel());
            requestBodyNode.set("messages", objectMapper.createArrayNode().add(messageNode));

            String requestBody = objectMapper.writeValueAsString(requestBodyNode);

            // 요청 본문 출력 (디버깅용)
            System.out.println("Request Body: " + requestBody);

            // RestTemplate 인스턴스 생성
            RestTemplate restTemplate = new RestTemplate();

            // 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth("");

            // 요청 엔터티 생성
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            // OpenAI API 호출
            ResponseEntity<String> response = restTemplate.exchange(
                    OPENAI_API_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            // 응답 본문 출력 (디버깅용)
            System.out.println("Response Body: " + response.getBody());

            // 응답 본문 파싱
            String responseBody = response.getBody();
            List<MyListResponse.ContentDTO> recommendedMovies = new ArrayList<>();
            if (responseBody != null) {
                // 응답 본문이 JSON 형식이 아닌 경우 텍스트 형식으로 처리
                if (responseBody.startsWith("{")) {
                    // JSON 형식인 경우
                    recommendedMovies = myListService.parseRecommendedMovies(responseBody);
                } else {
                    // 텍스트 형식인 경우
                    recommendedMovies = myListService.parseRecommendedMoviesFromText(responseBody);
                }
            }

            // 추천 영화 목록 출력
            recommendedMovies.forEach(movie -> System.out.println("Recommended Movie: " + movie));

            return ResponseEntity.ok(recommendedMovies);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/api/my-favorite-list")
    public String getMyFavList(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        MyListResponse.MyListDTO respDTO = myListService.findMyListById(sessionUser.getId());
        String userMessage =myListService.getOpenAi();
        MyListRequest.OpenAIRequest openAIRequest = new MyListRequest.OpenAIRequest("gpt-3.5-turbo-0125", userMessage);
        List<MyListResponse.ContentDTO> recommendedMovies = processOpenAIRequest(openAIRequest).getBody();
        //여기서부터하기!!!

        request.setAttribute("MyListDTO", respDTO);
        request.setAttribute("openAIRequest", openAIRequest);
        return "mylist/my-favorite-list";
    }

    @PostMapping("/api/users/{contentId}/favorite")
    public ResponseEntity<?> addFavorite(@PathVariable int contentId, MyListRequest.AddFavoriteDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(sessionUser.getId());
        reqDTO.setContentId(contentId);

        myListService.addFavorite(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(true));  // 찜 성공 여부를 JSON으로 반환
    }

    @PostMapping("/api/users/{contentId}/unfavorite")
    public ResponseEntity<?> removeFavorite(@PathVariable int contentId, MyListRequest.RemoveFavoriteDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(sessionUser.getId());
        reqDTO.setContentId(contentId);

        myListService.removeFavorite(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(true));  // 찜 취소 성공 여부를 JSON으로 반환
    }
}
