package com.project.ohflix.domain.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserNativeRepository {
    private final EntityManager em;

    public List<UserResponse.SalesPageUserDTO> findMonthlyUserStats(String startDate, String currentDate) {
        Query query = em.createNativeQuery(
                """
                SELECT
                    FORMATDATETIME(u.created_at, 'yyyy-MM'),
                    COUNT(*),
                    SUM(COUNT(*)) OVER (ORDER BY FORMATDATETIME(u.created_at, 'yyyy-MM'))
                FROM
                    user_tb u
                WHERE
                    u.created_at >= :startDate AND u.created_at < :currentDate
                GROUP BY
                    FORMATDATETIME(u.created_at, 'yyyy-MM')
                ORDER BY
                    FORMATDATETIME(u.created_at, 'yyyy-MM');
                """
        );

        query.setParameter("startDate", startDate);
        query.setParameter("currentDate", currentDate);

        List<Object[]> results = query.getResultList();

        List<UserResponse.SalesPageUserDTO> responseDTO = new ArrayList<>();

        for (Object[] result : results) {
            String yearMonth = (String) result[0];
            Long monthlyUserCount = ((Number) result[1]).longValue();
            Long cumulativeUserCount = ((Number) result[2]).longValue();
            System.out.println("cumulativeUserCount = " + cumulativeUserCount);
            responseDTO.add(new UserResponse.SalesPageUserDTO(yearMonth, monthlyUserCount,cumulativeUserCount));
        }
        return responseDTO;
    }

    public List<UserResponse.SalesPageSubscribeUserDTO> findSubscribeUserStats(String startDate, String currentDate) {
        Query query = em.createNativeQuery(
                """
                SELECT
                    FORMATDATETIME(u.created_at, 'yyyy-MM'),
                    COUNT(*)
                FROM
                    user_tb u
                WHERE
                    u.created_at >= :startDate AND u.created_at < :currentDate AND
                    u.is_subscribe = true
                GROUP BY
                    FORMATDATETIME(u.created_at, 'yyyy-MM')
                ORDER BY
                    FORMATDATETIME(u.created_at, 'yyyy-MM');
                """
        );

        query.setParameter("startDate", startDate);
        query.setParameter("currentDate", currentDate);

        List<Object[]> results = query.getResultList();

        List<UserResponse.SalesPageSubscribeUserDTO> responseDTO = new ArrayList<>();

        for (Object[] result : results) {
            String yearMonth = (String) result[0];
            Long subscribeUserCount = ((Number) result[1]).longValue();

            responseDTO.add(new UserResponse.SalesPageSubscribeUserDTO(yearMonth, subscribeUserCount));
        }

        return responseDTO;
    }
}