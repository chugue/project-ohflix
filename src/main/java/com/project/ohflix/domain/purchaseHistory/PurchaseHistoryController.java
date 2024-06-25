package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix.domain.cardInfo.CardInfoResponse;
import com.project.ohflix.domain.cardInfo.CardInfoService;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.content.ContentResponse;
import com.project.ohflix.domain.content.ContentService;
import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PurchaseHistoryController {

    private final HttpSession session;
    private final PurchaseHistoryService purchaseHistoryService;
    private final ContentRepository contentRepository;
    private final ContentService contentService;
    private final CardInfoService cardInfoService;

    @GetMapping("/api/paymethod-form")
    public String getPaymethodRegisterForm() {
        return "paymethod/paymethod-register-form";
    }

    @GetMapping("/api/paymethod-manage")
    public String getPaymethodManage(HttpServletRequest request) {

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<CardInfoResponse.paymethodManageDTO> respDTO = purchaseHistoryService.paymethodManagePage(sessionUser.getId());
        request.setAttribute("paymethodManageDTO", respDTO);
        return "paymethod/paymethod-manage";
    }

    @GetMapping("/api/paymethod-update-form/{cardInfoId}")
    public String getPaymethodUpdateForm(@PathVariable("cardInfoId") Integer cardInfoId, HttpServletRequest request) {
        CardInfoResponse.DetailDTO respDTO = cardInfoService.findCardInfoById(cardInfoId);
        request.setAttribute("DetailDTO", respDTO);
        return "paymethod/paymethod-update-form";
    }

    @GetMapping("/api/purchase-histories")
    public String getPayment(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PurchaseHistoryResponse.purchaseHistoryDTO respDTO = purchaseHistoryService.purchaseHistories(sessionUser.getId());
        request.setAttribute("purchaseHistoryDTO", respDTO);

        return "paymethod/purchase-histories";
    }


    @GetMapping("/api/account-security")
    public String accountSecurity(HttpServletRequest request) {
        // accountSecurityPage 데이터 바인딩
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PurchaseHistoryResponse.AccountSecurityDTO respDTO = purchaseHistoryService.accountSecurityPage(sessionUser.getId());
        request.setAttribute("AccountSecurityDTO", respDTO);

        return "account/account-security";
    }

    @GetMapping("/admin/admin-upload")
    public String adminUpload() {
        return "admin/admin-upload";
    }

    @GetMapping("/admin/content-update-link")
    public String contentUpdateLink() {
        // contentUpdateLinkPage 데이터 바인딩
        ContentResponse.ContentUpdateLinkPageDTO respDTO = contentService.contentUpdateLinkPageDTO(1);
        session.setAttribute("ContentUpdateLinkDTO", respDTO);

        return "admin/content-update-link";
    }

    @GetMapping("/api/video-manage")
    public String videoManage() {
        // videoManagePage 데이터 바인딩
        ContentResponse.VideoManagePageDTO respDTO = contentService.videoManagePageDTO();
        session.setAttribute("VideoManagePageDTO", respDTO);

        return "admin/video-manage";
    }


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
