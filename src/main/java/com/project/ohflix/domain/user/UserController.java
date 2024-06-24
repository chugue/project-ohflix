package com.project.ohflix.domain.user;

import com.project.ohflix._core.utils.EnumEditor;
import com.project.ohflix.domain._enums.Reason;
import com.project.ohflix.domain.refund.RefundRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession httpSession;
    private final UserService userService;

    @GetMapping("/login-form")
    public String getLoginForm() {return "user/login-form";}


    // 사용자 환불요청 페이지
    @GetMapping("/api/refund-request-form")
    public String getAccountRefundPage(HttpServletRequest request) {
        Integer sessionUserId = 2;
        request.setAttribute("sessionUserId", sessionUserId);
        return "account/refund-request-form";
    }

    // 환불 액션
    @PostMapping("/refund")
    public String refund(RefundRequest.RequestDTO reqDTO) {
        userService.requestRefund(reqDTO);
        return "redirect:/api/account-view";
    }


    // 사용자 확인 방법 선택 페이지
    @GetMapping("/api/user-check")
    public String getUserCheck(HttpServletRequest request) {
        Integer sessionUserId = 2; //TODO : 세션이 구현되면 세션 사용자 아이디가 들어가야됨
        UserResponse.UserCheckDTO respDTO =userService.userCheckPage(sessionUserId);
        request.setAttribute("UserCheckDTO", respDTO);
        return "user/user-check";
    }

    // 사용자 프로필 변경 페이지 TODO : SessionUserID 넣기
    @GetMapping("/api/profile-form")
    public String getProfileView(HttpServletRequest request) {
        User sessionUser = (User) httpSession.getAttribute("sessionUser");
//        User respDTO = userService.userProfileForm(sessionUser.getId());
        UserResponse.ProfileFormDTO respDTO = userService.userProfileForm(4);
        request.setAttribute("UserProfileFormDTO", respDTO);
        return "profile/profile-form";
    }

    // YSH : 맴버십 취소 페이지
    @GetMapping("/api/cancel-plan")
    public String getCancelPlan(HttpServletRequest request) {
        User sessionUser = (User) httpSession.getAttribute("sessionUser");
//        UserResponse.CancelPlanPageDTO respDTO = userService.userCanclePlan(sessionUser.getId());
        UserResponse.CancelPlanPageDTO respDTO = userService.userCanclePlan(2);

        request.setAttribute("CancelPlanPageDTO", respDTO);
        return "user/cancel-plan";
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


    @GetMapping("/api/account-view")
    public String getAccountPage() {
        return "account/account-view";
    }



    @GetMapping("/api/account-membership")
    public String getAccountMembership() {
        return "account/account-membership";
    }



    @GetMapping("/api/profile-setting")
    public String profileSetting(HttpServletRequest request) {

        //SessionUser user=session.getAttribute("sessionUser");
        UserResponse.ProfileSettingDTO respDTO= userService.profileSetting(2);
        request.setAttribute("ProfileSettingDTO",respDTO);
        return "profile/profile-setting";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Reason.class, new EnumEditor<>(Reason.class));
        // 다른 enum 타입에 대해 추가 등록 가능
        // binder.registerCustomEditor(AnotherEnum.class, new EnumEditor<>(AnotherEnum.class));
    }
}
