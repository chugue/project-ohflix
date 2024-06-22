package com.project.ohflix;

import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.profileIcon.ProfileIconRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CoontentRepositoryTest {
    @Autowired
    private ContentRepository contentRepository;

    @Test
    public void latestContentRepository_test(){
        // given


        // when
        contentRepository.findALLByOrderByIdDesc();

        // then

    }
}
