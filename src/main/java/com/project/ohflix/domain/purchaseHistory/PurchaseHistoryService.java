package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.content.ContentResponse;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import com.project.ohflix.domain.cardInfo.CardInfoResponse;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {

    private final UserRepository userRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final CardInfoRepository cardInfoRepository;
    private final ContentRepository contentRepository;

    //paymethod-manage
    public List<CardInfoResponse.paymethodManageDTO> paymethodManagePage(int userId) {
        User user=userRepository.findById(userId).orElseThrow(() -> new Exception404("유저 정보가 없습니다."));

        List<CardInfo> cardInfos = cardInfoRepository.findByUserId(user.getId()).orElseThrow(() -> new Exception404("등록하신 카드가 없습니다."));

        List<CardInfoResponse.paymethodManageDTO> respDTO = cardInfos.stream().map(CardInfoResponse.paymethodManageDTO::new).toList();

        return respDTO;
    }

    public PurchaseHistoryResponse.purchaseHistoryDTO purchaseHistories(int userId) {
        User user=userRepository.findById(userId).orElseThrow(() -> new Exception404("유저 정보가 없습니다."));

        List<PurchaseHistory> purchaseHistories= purchaseHistoryRepository.findByUserId(user.getId()).orElseThrow(() -> new Exception404("결제내역이 없습니다."));

        return new PurchaseHistoryResponse.purchaseHistoryDTO(purchaseHistories);
    }

    // AccountSecurityPage (email, mobile 불러오기)
    @Transactional
    public PurchaseHistoryResponse.AccountSecurityDTO accountSecurityPage(int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));

        return new PurchaseHistoryResponse.AccountSecurityDTO(user);
    }
}





