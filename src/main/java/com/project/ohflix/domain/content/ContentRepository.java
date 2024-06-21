package com.project.ohflix.domain.content;

import com.project.ohflix.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Integer> {

    // contentUpdateLinkPage
    Optional<Content> findById(@Param("id") Integer id);
}
