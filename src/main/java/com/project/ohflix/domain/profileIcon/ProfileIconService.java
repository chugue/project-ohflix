package com.project.ohflix.domain.profileIcon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileIconService {

    private final ProfileIconRepository profileIconRepository;


    public List<ProfileIconResponse.ProfileIconListDTO> findProfileIconList() {
        List<ProfileIcon> profileIconList = profileIconRepository.findAll();
        System.out.println(profileIconList);

        return profileIconList.stream().map(profileIcon
                -> new ProfileIconResponse.ProfileIconListDTO(profileIcon)).toList();
    }
}





