package com.project.ohflix.domain.user;

import com.project.ohflix._core.utils.RedisUtil;
import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import com.project.ohflix.domain.refund.RefundResponse;
import com.project.ohflix.domain.refund.RefundService;
import com.project.ohflix._core.utils.EnumEditor;
import com.project.ohflix.domain._enums.Reason;
import com.project.ohflix.domain.refund.RefundRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserService userService;
    private final RefundService refundService;
    private final RedisTemplate<String, Object> redisTemplate;


    @GetMapping("/login-form")
    public String getLoginForm() {

        return "user/login-form";
    }

    // kakao 로그인
    //http://localhost:8080/oauth/kakao/callback
    @GetMapping("/oauth/kakao/callback")
    public String oauthKakaoCallback(String kakaoAccessToken) {
        System.out.println("우와 콜백됐다!" + kakaoAccessToken);
        User sessionUser = userService.kakaoLogin(kakaoAccessToken);
        session.setAttribute("sessionUser", sessionUser);

        return "redirect:/";
    }


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
        UserResponse.UserCheckDTO respDTO = userService.userCheckPage(sessionUserId);
        request.setAttribute("UserCheckDTO", respDTO);
        return "user/user-check";
    }

    // 사용자 프로필 변경 페이지 TODO : SessionUserID 넣기
    @GetMapping("/api/profile-form")
    public String getProfileView(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
//        User respDTO = userService.userProfileForm(sessionUser.getId());
        UserResponse.ProfileFormDTO respDTO = userService.userProfileForm(4);
        request.setAttribute("UserProfileFormDTO", respDTO);
        return "profile/profile-form";
    }

    // YSH : 멥버십 취소 페이지 TODO : SessionUserID 넣기
    @GetMapping("/api/cancel-plan")
    public String getCancelPlan(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.CancelPlanPageDTO respDTO = userService.userCanclePlan(2);

        request.setAttribute("CancelPlanPageDTO", respDTO);
        return "user/cancel-plan";
    }

    // YSH : 멤버십 상세정보 페이지 TODO : SessionUserID 넣기
    @GetMapping("/api/account-membership")
    public String getAccountMembership(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.AccountMembershipDTO respDTO = userService.accountMembership(3);

        request.setAttribute("AccountMembershipDTO", respDTO);

        return "account/account-membership";
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
    public String getAccountPage(HttpServletRequest request) {
        Integer sessionUserId = 3; // 임의의 세션 유저 ID
        UserResponse.AccountMembershipInfoDTO respDTO = userService.accountMembershipInfo(sessionUserId);

        request.setAttribute("accountMembershipInfo", respDTO);
        return "account/account-view";
    }

    @GetMapping("/api/profile-setting")
    public String profileSetting(HttpServletRequest request) {
        SessionUser sessionUser=(SessionUser) session.getAttribute("sessionUser");
        UserResponse.ProfileSettingDTO respDTO= userService.profileSetting(sessionUser.getId());
        request.setAttribute("ProfileSettingDTO",respDTO);
        return "profile/profile-setting";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Reason.class, new EnumEditor<>(Reason.class));
        // 다른 enum 타입에 대해 추가 등록 가능
        // binder.registerCustomEditor(AnotherEnum.class, new EnumEditor<>(AnotherEnum.class));
    }


    @PostMapping("/login")
    public String login(HttpSession session, UserRequest.LoginDTO requestDTO) {
        SessionUser responseDTO = userService.login(requestDTO);

        redisTemplate.opsForValue().set("sessionUser", responseDTO);
        session.setAttribute("sessionUser", requestDTO);
        return "redirect:/api/main-page";
    }

//    @GetMapping("/logout")
//    public String logout(){
//        redisTemplate.deleteSessionUser();
//
//        return "redirect:/login-form";
//    }
}
