package com.project.ohflix.domain.payment;

import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain._enums.Paymethod;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistoryRepository;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final UserRepository userRepository;
    private final CardInfoRepository cardInfoRepository;

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

    @Transactional
    public PurchaseHistory savePaymentTest(CreditPayRequest.CreditPayTestDTO reqDTO, Integer sessionUserId) {
        // 조회 및 예외 처리
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("유저를 찾을 수 없습니다."));

        List<CardInfo> cardInfo = cardInfoRepository.findByUserId(sessionUserId)
                .orElseThrow(() -> new Exception404("해당 카드를 찾을 수 없습니다."));

        PurchaseHistory purchaseHistory = new PurchaseHistory();

        purchaseHistory.setUser(user);
        purchaseHistory.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        purchaseHistory.setDescription("스트리밍 서비스");
        purchaseHistory.setPaymethod(Paymethod.CREDITCARD);
        purchaseHistory.setCardInfo(cardInfo.getFirst());
        purchaseHistory.setAmount(13500);

        // 한 달 뒤의 날짜 계산
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(System.currentTimeMillis()));
        calendar.add(Calendar.MONTH, 1);
        Timestamp oneMonthLaterTimestamp = new Timestamp(calendar.getTimeInMillis());

        // 파싱
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = sdf.format(new Timestamp(System.currentTimeMillis()));
        String endDate = sdf.format(oneMonthLaterTimestamp);

        purchaseHistory.setServicePeriod(startDate + "~" + endDate); // 서비스 기간 파싱


        return purchaseHistoryRepository.save(purchaseHistory);
    }


}
