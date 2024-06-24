package com.project.ohflix;

import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.refund.Refund;
import com.project.ohflix.domain.refund.RefundRepository;
import com.project.ohflix.domain.refund.RefundRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RefundRepositoryTest {
    @Autowired
    private RefundRepository refundRepository;
}
