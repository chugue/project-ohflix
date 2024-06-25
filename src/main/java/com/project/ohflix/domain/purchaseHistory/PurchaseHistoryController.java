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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/api/payment/ready")
    @ResponseBody
    public PurchaseHistoryResponse.KakaoPayReadyDTO readyToPay(@RequestParam int userId, @RequestParam String itemName, @RequestParam int totalAmount, @RequestParam int vatAmount) {
        return purchaseHistoryService.preparePayment(userId, itemName, totalAmount, vatAmount);
    }
    // 결제 승인 API 엔드포인트
    @GetMapping("/api/payment/success")
    public String paymentSuccess(@RequestParam String pg_token, @RequestParam int userId) {
        String tid = "T1234567890123456789"; // 실제로는 저장된 tid를 사용
        PurchaseHistoryResponse.KakaoPayApproveDTO response = purchaseHistoryService.approvePayment(userId, tid, pg_token);
        // 승인 후 처리 로직 추가
        return "redirect:/api/paymethod-manage";
    }

    @GetMapping("/api/payment/fail")
    public String paymentFail() {
        return "redirect:/api/paymethod-manage?status=fail";
    }

    @GetMapping("/api/payment/cancel")
    public String paymentCancel() {
        return "redirect:/api/paymethod-manage?status=cancel";
    }

    // 정기 결제 요청 API 엔드포인트
    @PostMapping("/api/payment/subscription")
    @ResponseBody
    public Map<String, Object> subscriptionPayment(@RequestParam int userId, @RequestParam String sid, @RequestParam int totalAmount) {
        return purchaseHistoryService.subscriptionPayment(userId, sid, totalAmount);
    }

    // 정기 결제 비활성화 API 엔드포인트
    @PostMapping("/api/payment/deactivate")
    @ResponseBody
    public Map<String, Object> deactivateSubscription(@RequestParam int userId, @RequestParam String sid) {
        return purchaseHistoryService.deactivateSubscription(userId, sid);
    }

    // 정기 결제 상태 조회 API 엔드포인트
    @PostMapping("/api/payment/status")
    @ResponseBody
    public Map<String, Object> checkSubscriptionStatus(@RequestParam int userId, @RequestParam String sid) {
        return purchaseHistoryService.checkSubscriptionStatus(userId, sid);
    }
}
