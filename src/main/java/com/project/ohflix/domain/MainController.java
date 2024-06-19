package com.project.ohflix.domain;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {




    // 성훈
    @GetMapping("/11")
    public String getLoginForm() {
        return "user/login-form";
    }

    @GetMapping("/12")
    public String getPaymethodRegisterForm() {
        return "paymethod/paymethod-form";
    }

    @GetMapping("/13")
    public String getPaymethodManage() {
        return "paymethod/paymethod-manage";
    }

    @GetMapping("/14")
    public String getPaymethodUpdateForm() {
        return "paymethod/aymethod-update-form";
    }
    @GetMapping("/15")
    public String getUserCheck() {
        return "user/user-check";
    }



    // 동기
    @GetMapping("/21")
    public String getPasswordChangeForm() {
        return "user/password-change-form";
    }

    @GetMapping("/22")
    public String getProfileIcons() {
        return "profile/profile-icons";
    }

    @GetMapping("/23")
    public String getRestrictionPass() {
        return "restriction/restriction-pass";
    }

    @GetMapping("/24")
    public String getRestrictionManage() {
        return "restiction/restriction-manage";
    }


    // 지영
    @GetMapping("/31")
    public String getPayment() {
        return "paymethod/payment-form";
    }
    @GetMapping("/32")
    public String getSales() {
        return "user/sales-page";
    }
    @GetMapping("/33")
    public String getMembers() {
        return "members-manage";
    }

    // 승호
    @GetMapping("/")
    public String getMainPage() {
        return "main-page";
    }
    @GetMapping("/40")
    public String getOuterPage() {
        return "outer-page";
    }
    @GetMapping("/41")
    public String getMyList() {
        return "my-list";
    }
    @GetMapping("/42")
    public String getLatest() {
        return "content/content-latest";
    }




    // account
    @GetMapping("/50")
    public String getAccountPage() {
        return "account/account-view";
    }

    // account
    @GetMapping("/51")
    public String accountMembership() {
        return "account/account-membership";
    }

    // account
    @GetMapping("/52")
    public String profileSetting() {
        return "profile/profile-setting";
    }

    // account
    @GetMapping("/53")
    public String accountSecurity() {
        return "account/account-security";
    }

    // admin
    @GetMapping("/54")
    public String adminUpload() {
        return "admin-upload";
    }

    @PostMapping("/upload/movie")
    public String uploadMovie() {
        return null;
    }

    @PostMapping("/upload/info")
    public String uploadInfo() {
        return null;
    }

    @GetMapping("/3")
    public String getContentDetails() {
        return "content/content-details";
    }

    @GetMapping("/5")
    public String getProfileView() {
        return "profile/profile-form";
    }




}
