package com.project.ohflix.domain.payment;

import com.project.ohflix._core.utils.ApiUtil;
import com.project.ohflix.domain.content.ContentResponse;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final HttpSession session;

    @PostMapping("api/pay/info")
    public ResponseEntity<?> getContentInfo(@RequestBody CreditPayRequest.CreditPayDTO reqDTO){
        paymentService.savePayment(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(paymentService.savePayment(reqDTO)));
    }

    @PostMapping("/save-payment")
    public PurchaseHistory savePayment(@RequestBody CreditPayRequest.CreditPayTestDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        return paymentService.savePaymentTest(reqDTO, sessionUser.getId());
    }
}
