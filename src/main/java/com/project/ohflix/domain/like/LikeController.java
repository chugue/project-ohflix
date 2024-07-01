package com.project.ohflix.domain.like;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class LikeController {
    private final HttpSession session;
    private final LikeService likeService;




}
