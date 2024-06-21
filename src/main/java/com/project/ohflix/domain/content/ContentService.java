package com.project.ohflix.domain.content;

import com.project.ohflix.domain.profileIcon.ProfileIconResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    public List<ProfileIconResponse.ProfileIconListDTO> findLatestContent() {

        return List.of();
    }
}





