package com.project.ohflix.domain.content;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ContentController {

    @GetMapping("/api/latest-content")
    public String getLatest() {
        return "content/latest-content";
    }

    @GetMapping("/api/content-details")
    public String getContentDetails() {
        return "content/content-details";
    }


}
