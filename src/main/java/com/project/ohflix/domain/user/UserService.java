package com.project.ohflix.domain.user;

import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CardInfoRepository cardInfoRepository;


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

}





