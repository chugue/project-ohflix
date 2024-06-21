package com.project.ohflix.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    @GetMapping("/login-form")
    public String getLoginForm() {
        return "user/login-form";
    }

    @GetMapping("/api/user-check")
    public String getUserCheck() {
        return "user/user-check";
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
    public String getMembers() {
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

    @GetMapping("/api/cancel-plan")
    public String getCancelPlan() {
        return "user/cancel-plan";
    }
}
