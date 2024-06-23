package com.project.ohflix;

import com.project.ohflix.domain.purchaseHistory.PurchaseHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PurchaseHistoryRepositoryTest {

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;
    @Test
    public void findById_test(){
        // given
        int id =2;

        // when
        purchaseHistoryRepository.findByUser(2);

        // then

    }
}
