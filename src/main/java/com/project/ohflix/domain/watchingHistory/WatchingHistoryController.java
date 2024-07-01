package com.project.ohflix.domain.watchingHistory;

import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class WatchingHistoryController {
    private final HttpSession session;
    private final WatchingHistoryService watchingHistoryService;


    @GetMapping("/api/view-history")
    public String getViewed(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        WatchingHistoryResponse.WatchingHistoryDTO respDTO =watchingHistoryService.getWatchingHistory(sessionUser.getId());
        request.setAttribute("watchingHistory", respDTO);
        return "user/view-history";
    }
}
