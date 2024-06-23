package com.project.ohflix.domain.refund;

import com.project.ohflix.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Integer> {
}
