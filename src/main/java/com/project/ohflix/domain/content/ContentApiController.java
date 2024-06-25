package com.project.ohflix.domain.content;

import com.project.ohflix._core.utils.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ContentApiController {
    private final ContentService contentService;


    // 메인페이지 모달 영화 정보 가져오기
    @GetMapping("/api/content-info/{contentId}")
    public ResponseEntity<?> getContentInfo(@PathVariable Integer contentId){
        ContentResponse.DetailsDTO respDTO= contentService.getContentDetails(contentId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
