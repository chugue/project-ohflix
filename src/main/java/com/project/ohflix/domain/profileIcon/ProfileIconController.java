package com.project.ohflix.domain.profileIcon;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProfileIconController {
    private final ProfileIconService profileIconService;

    @GetMapping("/api/profile-icons")
    public String getProfileIcons(HttpServletRequest request) {
        List<ProfileIconResponse.ProfileIconListDTO> profileIconList = profileIconService.findProfileIconList();
        request.setAttribute("profileIconList", profileIconList);
        return "profile/profile-icons";
    }
}
