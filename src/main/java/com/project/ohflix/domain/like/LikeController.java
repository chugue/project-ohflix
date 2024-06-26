package com.project.ohflix.domain.like;

import com.project.ohflix.domain.content.ContentResponse;
import com.project.ohflix.domain.mylist.MyListRequest;
import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class LikeController {
    private final HttpSession session;
    private final LikeService likeService;

    // 좋아요 기능
    @PostMapping("/api/users/{contentId}/like")
    public String addLike(@PathVariable int contentId, LikeRequest.AddLikeDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(sessionUser.getId());
        reqDTO.setContentId(contentId);

        likeService.addLike(reqDTO);

        return "redirect:/api/main-page";
    }

    // 좋아요 취소 기능
    @PostMapping("/api/users/{contentId}/dislike")
    public String removeLike(@PathVariable int contentId, LikeRequest.RemoveLikeDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(sessionUser.getId());
        reqDTO.setContentId(contentId);

        likeService.removeLike(reqDTO);

        return "redirect:/api/main-page";
    }

}
