package com.project.ohflix.domain.refund;

import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import com.project.ohflix.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Integer> {

}
