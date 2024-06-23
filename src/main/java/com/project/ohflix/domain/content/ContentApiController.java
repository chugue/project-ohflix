package com.project.ohflix.domain.content;

import com.project.ohflix._core.utils.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ContentApiController {
    private final ContentService contentService;

    @GetMapping("/api/content-info")
    public ResponseEntity<?> getContentInfo(){
        Integer contentId = 3;
        ContentResponse.DetailsDTO respDTO= contentService.getContetnDetails(contentId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
