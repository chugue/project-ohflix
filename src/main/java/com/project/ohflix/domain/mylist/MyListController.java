package com.project.ohflix.domain.mylist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MyListController {

    @GetMapping("/api/my-favorite-list")
    public String getMyFavList() {
        return "mylist/my-favorite-list";
    }
}
