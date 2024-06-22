package com.project.ohflix.domain.user;

import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import com.project.ohflix.domain.refund.RefundResponse;
import com.project.ohflix.domain.refund.RefundService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserService userService;
    private final RefundService refundService;

    @GetMapping("/login-form")
    public String getLoginForm() {return "user/login-form";}

    // 사용자 확인 방법 선택 페이지
    @GetMapping("/api/user-check")
    public String getUserCheck(HttpServletRequest request) {
        Integer sessionUserId = 2; //TODO : 세션이 구현되면 세션 사용자 아이디가 들어가야됨
        UserResponse.UserCheckDTO respDTO =userService.userCheckPage(sessionUserId);
        request.setAttribute("UserCheckDTO", respDTO);
        return "user/user-check";
    }

    // 사용자 프로필 변경 페이지
    @GetMapping("/api/profile-form")
    public String getProfileView(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
//        User respDTO = userService.userProfileForm(sessionUser.getId());
        UserResponse.UserProfileFormDTO respDTO = userService.userProfileForm(4);
        request.setAttribute("user", respDTO);
        return "profile/profile-form";
    }

    @GetMapping("/api/view-history")
    public String getViewed() {
        return "user/view-history";
    }

    @GetMapping("/api/password-change-form")
    public String getPasswordChangeForm() {
        return "user/password-change-form";
    }

    @GetMapping("/api/restriction-pass")
    public String getRestrictionPass() {
        return "restriction/restriction-pass";
    }

    @GetMapping("/api/restriction-manage")
    public String getRestrictionManage() {
        return "restriction/restriction-manage";
    }

    @GetMapping("/api/sales-page")
    public String getSales() {
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

    @GetMapping("/api/account-view")
    public String getAccountPage() {
        return "account/account-view";
    }

    @GetMapping("/api/refund-page")
    public String getRefund(HttpServletRequest request) {
        // refindPage 데이터 바인딩
        RefundResponse.RefundPageDTO respDTO = refundService.refundPageDTO();
        request.setAttribute("RefundPageDTO", respDTO);

        return "user/refund-page";
    }

    @GetMapping("/api/account-membership")
    public String getAccountMembership() {
        return "account/account-membership";
    }

    @GetMapping("/api/cancel-plan")
    public String getCancelPlan() {
        return "user/cancel-plan";
    }


    @GetMapping("/api/profile-setting")
    public String profileSetting(HttpServletRequest request) {

        //SessionUser user=session.getAttribute("sessionUser");

        UserResponse.ProfileSettingDTO respDTO= userService.profileSetting(2);

        request.setAttribute("ProfileSettingDTO",respDTO);

        return "profile/profile-setting";
    }
}
