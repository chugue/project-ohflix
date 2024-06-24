package com.project.ohflix.domain.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession httpSession;
    private final UserService userService;

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

    @GetMapping("/api/account-view")
    public String getAccountPage() {
        return "account/account-view";
    }

    @GetMapping("/api/refund-page")
    public String getRefund() {


        return "user/refund-page";
    }

    @GetMapping("/api/account-membership")
    public String getAccountMembership() {
        return "account/account-membership";
    }




    @GetMapping("/api/profile-setting")
    public String profileSetting(HttpServletRequest request) {

        SessionUser user=(SessionUser) httpSession.getAttribute("sessionUser");
        System.out.println(user);
        UserResponse.ProfileSettingDTO respDTO= userService.profileSetting(user.getId());
        request.setAttribute("ProfileSettingDTO",respDTO);
        return "profile/profile-setting";
    }



    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqestDTO){
        System.out.println("reqestDTO = " + reqestDTO);
        SessionUser responseDTO=userService.login(reqestDTO);
        httpSession.setAttribute("sessionUser", responseDTO);
        return "redirect:/";
    }
}
