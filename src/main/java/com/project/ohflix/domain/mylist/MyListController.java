package com.project.ohflix.domain.mylist;

import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MyListController {
    private final HttpSession session;
    private final MyListService myListService;

    @GetMapping("/api/my-favorite-list")
    public String getMyFavList(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<MyListResponse.MyFavoriteListDTO> respDTOList = myListService.findMyListById(sessionUser.getId());
        request.setAttribute("myFavoriteList", respDTOList);
        return "mylist/my-favorite-list";
    }

    // 찜하기
    @PostMapping("/api/users/{contentId}/favorite")
    public String addFavorite(@PathVariable int contentId, MyListRequest.AddFavoriteDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(sessionUser.getId());
        reqDTO.setContentId(contentId);

        myListService.addFavorite(reqDTO);

        return "redirect:/my-favorite-list";
    }

    // 찜 취소
    @PostMapping("/api/users/{contentId}/unfavorite")
    public String RemoveFavorite(@PathVariable int contentId, MyListRequest.RemoveFavoriteDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(sessionUser.getId());
        reqDTO.setContentId(contentId);

        myListService.removeLike(reqDTO);

        return "redirect:/my-favorite-list";
    }
}
