package com.project.ohflix.domain.profileIcon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ProfileIconController {

    @GetMapping("/api/profile-icons")
    public String getProfileIcons() {
        return "profile/profile-icons";
    }
}
