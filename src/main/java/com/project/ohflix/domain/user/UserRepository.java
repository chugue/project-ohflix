package com.project.ohflix.domain.user;

import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public interface UserRepository extends JpaRepository<User, Integer> {

    // loginPage
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    // accountSecurityPage
    Optional<User> findById(@Param("id") Integer id);

    @Query("""
            SELECT u
            FROM User u
            JOIN FETCH u.profileIcon
            WHERE u.id = :id
            """)
    User findUserProfileById(@Param("id") Integer id);

    @Query("""
            select u from User u join fetch u.profileIcon pu where u.id = :userId
            """)
    Optional<User> findUsernameAndIcon(@Param("userId") Integer userId);

}
