package com.project.ohflix.domain.user;

import com.project.ohflix._core.error.exception.Exception401;
import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.profileIcon.ProfileIconRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CardInfoRepository cardInfoRepository;
    private final ProfileIconRepository profileIconRepository;

    // login-page
    public User getUser(UserRequest.LoginDTO reqDTO) { // login

        return userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("아이디 또는 비밀번호가 틀렸습니다."));
    }

    /**
     *  1. 카카오에서 사용자 정보 요청하기
     *  2. code 방식과 동일
     *  3. jwt(스프링서버) 생성해서 엡에게 전달
     */
    // kakaoLogin
    @Transactional
    public User kakaoLogin(String kakaoAccessToken) {

//        // 1. RestTemplate 객체 생성
//        RestTemplate rt = new RestTemplate();
//
//        // 2. 토큰으로 사용자 정보 받기 (PK, Email)
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//        headers.add("Authorization", "Bearer " + kakaoAccessToken);
//
//        HttpEntity<MultiValueMap<String, String>> request =
//                new HttpEntity<>(headers);
//
//        ResponseEntity<KakaoResponse.KakaoUserDTO> response = rt.exchange(
//                "https://kapi.kakao.com/v2/user/me",
//                HttpMethod.GET,
//                request,
//                KakaoResponse.KakaoUserDTO.class);
//
//        // 3. 해당정보로 DB조회 (있을수, 없을수)
//        String username = "kakao_" + response.getBody().getId();
//        User userPS = userRepository.findByEmail(username)
//                .orElse(null);
//
//        // 4. 있으면? - 조회된 유저정보 리턴
//        if (userPS != null) {
//            //return JwtUtil.create(userPS);
//        } else {
//            // 5. 없으면? - 강제 회원가입
//            User user = User.builder()
//                    .username(username)
//                    .password(UUID.randomUUID().toString())
//                    .email(response.getBody().getProperties().getNickname() + "@nate.com")
//                    .provider("kakao")
//                    .build();
//            User returnUser = userRepository.save(user);
//            //return JwtUtil.create(returnUser);
//        }


        return null;
    }

    // 시청레벨 설정에서 사용자 관람등급 가져오기
    public UserResponse.RestrictionLevelDTO UserRestrictionInfo(Integer sessionUserId) {
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));
        return new UserResponse.RestrictionLevelDTO(user);
    }

    // user-check 페이지 데이터
    public UserResponse.UserCheckDTO userCheckPage(Integer sessionUserId) {
        CardInfo cardInfo = cardInfoRepository.findUserInfo(sessionUserId)
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
        User user = userRepository.findUsernameAndIcon(userId).orElseThrow(() -> new Exception404("유저 정보가 없습니다."));
        UserResponse.ProfileSettingDTO respDTO = new UserResponse.ProfileSettingDTO(user);

        return respDTO;
    }
}





