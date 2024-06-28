package com.project.ohflix.domain.payment;

import com.project.ohflix._core.utils.ApiUtil;
import com.project.ohflix.domain.content.ContentResponse;
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

    @PostMapping("api/pay/info")
    public ResponseEntity<?> getContentInfo(@RequestBody CreditPayRequest.CreditPayDTO reqDTO){
        paymentService.savePayment(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(paymentService.savePayment(reqDTO)));
    }
}
