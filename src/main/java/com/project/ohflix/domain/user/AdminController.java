package com.project.ohflix.domain.user;


import com.project.ohflix.domain.refund.RefundResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final HttpSession httpSession;
    private final UserService userService;


    @GetMapping("/api/sales-page")
    public String getSales(HttpServletRequest request) {
        List<UserResponse.SalesPageDTO> respDTO = userService.salesPage();
        request.setAttribute("SalesPageDTO", respDTO);
        return "admin/sales-page";
    }

    @GetMapping("/admin/members-manage")
    public String getMembers(HttpServletRequest request) {
        List<UserResponse.MembersDTO> members = userService.MembersDTOList();
        long subscriberCount = members.stream().filter(UserResponse.MembersDTO::getIsSubscribe).count();
        request.setAttribute("members", members);
        request.setAttribute("subscriberCount", subscriberCount);
        return "admin/members-manage";
    }

    // 관리자 환불요청 관리 페이지 데이터
    @GetMapping("/admin/refund-manage")
    public String getRefund(HttpServletRequest request) {
        RefundResponse.ListDTO respDTO = userService.getRefundBoard();
        request.setAttribute("ListDTO", respDTO);
        return "admin/refund-manage";
    }
}
