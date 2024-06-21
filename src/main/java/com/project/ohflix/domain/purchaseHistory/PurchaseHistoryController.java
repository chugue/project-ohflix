package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix.domain.cardInfo.CardInfoResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PurchaseHistoryController {

    private final HttpSession session;
    private final PurchaseHistoryService purchaseHistoryService;

    @GetMapping("/api/paymethod-form")
    public String getPaymethodRegisterForm() {
        return "paymethod/paymethod-register-form";
    }

    @GetMapping("/api/paymethod-manage")
    public String getPaymethodManage(HttpServletRequest request) {

        //SessionUser user=session.getAttribute("sessionUser");

        //유저정보를 넣을 수 없어서 2번유저를 바로 넣음!
        List<CardInfoResponse.paymethodManageDTO> respDTO=purchaseHistoryService.paymethodManagePage(2);

        request.setAttribute("card", respDTO);
        return "paymethod/paymethod-manage";
    }

    @GetMapping("/api/paymethod-update-form")
    public String getPaymethodUpdateForm(HttpServletRequest request) {

        return "paymethod/paymethod-update-form";
    }

    @GetMapping("/api/purchase-histories")
    public String getPayment(HttpServletRequest request) {

        //SessionUser user=session.getAttribute("sessionUser");

        //유저정보를 넣을 수 없어서 2번유저를 바로 넣음!
        PurchaseHistoryResponse.purchaseHistoryDTO respDTO=purchaseHistoryService.purchaseHistories(2);
        System.out.println("respDTO = " + respDTO);
        request.setAttribute("purchardHistories", respDTO);

        return "paymethod/purchase-histories";
    }

    @GetMapping("/api/profile-setting")
    public String profileSetting() {
        return "profile/profile-setting";
    }


    @GetMapping("/api/account-security")
    public String accountSecurity() {
        // accountSecurityPage 데이터 바인딩
        PurchaseHistoryResponse.AccountSecurityDTO respDTO = purchaseHistoryService.AccountSecurityPage(2);
        session.setAttribute("AccountSecurityDTO", respDTO);

        return "account/account-security";
    }

    @GetMapping("/admin/admin-upload")
    public String adminUpload() {
        return "admin/admin-upload";
    }

    @GetMapping("/admin/content-update-link")
    public String contentUpdateLink() {
        return "admin/content-update-link";
    }

    @GetMapping("/api/video-manage")
    public String videoManage() {return "admin/video-manage";}



    @PostMapping("/upload/movie")
    public String uploadMovie() {
        return null;
    }

    @PostMapping("/upload/info")
    public String uploadInfo() {
        return null;
    }

    @PostMapping("/update/movie")
    public String updateMovie() {
        return null;
    }

    @PostMapping("/update/info")
    public String updateInfo() {
        return null;
    }
}
