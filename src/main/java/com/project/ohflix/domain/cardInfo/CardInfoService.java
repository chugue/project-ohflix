package com.project.ohflix.domain.cardInfo;

import com.project.ohflix._core.error.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardInfoService {
    private final CardInfoRepository cardInfoRepository;

    // 카드 정보 업데이트 페이지 데이터
    public void findCardInfoById(Integer cardInfoId) {
        CardInfo cardInfo = cardInfoRepository.findCardInfoById(cardInfoId)
                .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));
    }
}





