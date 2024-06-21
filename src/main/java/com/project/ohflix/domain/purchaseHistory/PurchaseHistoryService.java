package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import com.project.ohflix.domain.cardInfo.CardInfoResponse;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {

    private final UserRepository userRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final CardInfoRepository cardInfoRepository;

    //paymethod-manage
    public CardInfoResponse.CardNumber paymethodManagePage(int userId) {
        User user=userRepository.findById(userId).orElseThrow();

        CardInfo cardInfo = cardInfoRepository.findByUserId(user.getId()).orElseThrow();

        CardInfoResponse.CardNumber respDTO = new CardInfoResponse.CardNumber(cardInfo.getCardNumber());

        return respDTO;
    }
}





