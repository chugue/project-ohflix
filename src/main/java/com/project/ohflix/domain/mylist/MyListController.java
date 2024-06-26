package com.project.ohflix.domain.mylist;

import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MyListController {
    private final HttpSession httpSession;
    private final MyListService myListService;

    @GetMapping("/api/my-favorite-list")
    public String getMyFavList(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("sessionUser");
        List<MyListResponse.MyFavoriteListDTO> respDTOList = myListService.findMyListById(sessionUser.getId());
        request.setAttribute("myFavoriteList", respDTOList);
        return "mylist/my-favorite-list";
    }
}
