package com.project.ohflix.domain.mylist;

import com.project.ohflix._core.utils.ApiUtil;
import com.project.ohflix.domain.user.SessionUser;
import com.project.ohflix.domain.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class MyListController {
    private final HttpSession session;
    private final MyListService myListService;
    private final UserService userService;

    @GetMapping("/api/my-favorite-list")
    public String getMyFavList(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        MyListResponse.MyListDTO respDTO = myListService.findMyListById(sessionUser.getId());
        System.out.println("respDTO = " + respDTO);
        request.setAttribute("MyListDTO", respDTO);
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
