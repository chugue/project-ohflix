package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {

    private final UserRepository userRepository;

    // AccountSecurityPage (email, mobile 불러오기)
    @Transactional
    public PurchaseHistoryResponse.AccountSecurityDTO AccountSecurityPage(int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));

        return new PurchaseHistoryResponse.AccountSecurityDTO(user);
    }


}





