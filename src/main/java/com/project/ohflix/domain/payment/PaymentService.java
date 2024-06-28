package com.project.ohflix.domain.payment;

import com.project.ohflix.domain._enums.Paymethod;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PurchaseHistoryRepository purchaseHistoryRepository;

    @Transactional
    public PurchaseHistory savePayment(CreditPayRequest.CreditPayDTO reqDTO) {
        PurchaseHistory payInfo = new PurchaseHistory();
        payInfo.setCreatedAt(reqDTO.getCreatedAt());
        payInfo.setDescription(reqDTO.getDescription());
        payInfo.setServicePeriod(reqDTO.getServicePeriod());
        payInfo.setPaymethod(Paymethod.CREDITCARD);
        payInfo.setCardInfo(CardInfo.builder().cardNumber(reqDTO.getCardNumber()).build());
        payInfo.setAmount(reqDTO.getAmount());
        //payInfo.setVat(creditPayRequest.getVat());
        //payInfo.setSupplyValue(creditPayRequest.getSupplyValue());

        System.out.println("❤🤍❤🤍❤🤍❤🤍 : " + reqDTO.getDescription());

        // 결제 정보를 데이터베이스에 저장
        return purchaseHistoryRepository.save(payInfo);
    }
}
