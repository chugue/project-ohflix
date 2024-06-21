package com.project.ohflix;

import com.project.ohflix.domain.profileIcon.ProfileIconRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class ProfileIconRepositoryTest {

    @Autowired
    private ProfileIconRepository profileIconRepository;

    @Test
    public void ProfileIconRepository_test(){
        // given


        // when
        profileIconRepository.findAll();

        // then

    }
}
