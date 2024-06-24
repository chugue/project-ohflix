package com.project.ohflix.domain.refund;

import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistoryResponse;
import com.project.ohflix.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundService {

    private final RefundRepository refundRepository;

}
