package com.project.ohflix.domain.user;

import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.profileIcon.ProfileIconRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CardInfoRepository cardInfoRepository;
    private final ProfileIconRepository profileIconRepository;

    // 시청레벨 설정에서 사용자 관람등급 가져오기
    public UserResponse.RestrictionLevelDTO UserRestrictionInfo(Integer sessionUserId) {
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));
        return new UserResponse.RestrictionLevelDTO(user);
    }

    // user-check 페이지 데이터
    public UserResponse.UserCheckDTO userCheckPage(Integer sessionUserId) {
       CardInfo cardInfo =  cardInfoRepository.findUserInfo(sessionUserId)
               .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));

      return new UserResponse.UserCheckDTO(cardInfo);
    }

    public List<UserResponse.MembersDTO> MembersDTOList() {
        return userRepository.findAll().stream()
                .map(UserResponse.MembersDTO::new)
                .collect(Collectors.toList());
    }

    public UserResponse.UserProfileFormDTO userProfileForm(Integer sessionUserId) {
        User userProfile = userRepository.findUserProfileById(sessionUserId);
        System.out.println(userProfile);
        return new UserResponse.UserProfileFormDTO(userProfile);
    }

    //profile-setting 프로필 세팅 페이지
    public UserResponse.ProfileSettingDTO profileSetting(int userId) {
        User user=userRepository.findUsernameAndIcon(userId).orElseThrow(() -> new Exception404("유저 정보가 없습니다."));
        UserResponse.ProfileSettingDTO respDTO=new UserResponse.ProfileSettingDTO(user);

        return respDTO;
    }
}





