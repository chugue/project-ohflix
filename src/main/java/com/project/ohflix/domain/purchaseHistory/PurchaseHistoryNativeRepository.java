package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix.domain.user.UserResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PurchaseHistoryNativeRepository {

    private final EntityManager em;

    public List<PurchaseHistoryResponse.SalesPageSalesDTO> findMonthlySalesStats(String startDate, String currentDate) {
        Query query = em.createNativeQuery(
                """
                        SELECT
                        FORMATDATETIME(ph.created_at, 'yyyy-MM'),
                        SUM(ph.amount)
                        FROM
                        purchase_history_tb ph
                        WHERE
                        ph.created_at >= :startDate AND ph.created_at < :currentDate
                        GROUP BY
                        FORMATDATETIME(ph.created_at, 'yyyy-MM')
                        ORDER BY 
                        FORMATDATETIME(ph.created_at, 'yyyy-MM')
                        """
                     );

        query.setParameter("startDate", startDate);
        query.setParameter("currentDate", currentDate);

        List<Object[]> results = query.getResultList();
        List<PurchaseHistoryResponse.SalesPageSalesDTO> responseDTO = new ArrayList<>();

        for (Object[] result : results) {
            String yearMonth = (String) result[0];
            Long monthlySales = ((Number) result[1]).longValue();


            responseDTO.add(new PurchaseHistoryResponse.SalesPageSalesDTO(yearMonth, monthlySales));
        }

        return responseDTO;
    }
}
