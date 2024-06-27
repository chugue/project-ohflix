package com.project.ohflix.domain.user;

import com.project.ohflix._core.error.exception.Exception400;
import com.project.ohflix._core.error.exception.Exception401;
import com.project.ohflix._core.error.exception.Exception403;
import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain._enums.Refuse;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.profileIcon.ProfileIconRepository;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistoryNativeRepository;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistoryRepository;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistoryResponse;
import com.project.ohflix.domain.refund.Refund;
import com.project.ohflix.domain.refund.RefundRepository;
import com.project.ohflix.domain.refund.RefundRequest;
import com.project.ohflix.domain.refund.RefundResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserNativeRepository userNativeRepository;
    private final CardInfoRepository cardInfoRepository;
    private final ProfileIconRepository profileIconRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final ContentRepository contentRepository;
    private final PurchaseHistoryNativeRepository purchaseHistoryNativeRepository;
    private final RefundRepository refundRepository;
    private final RedisTemplate<String, Object> redisTemplate;


    // login-page
    public User getUser(UserRequest.LoginDTO reqDTO) { // login

        return userRepository.findUserByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("아이디 또는 비밀번호가 틀렸습니다."));
    }

    /**
     * 1. 카카오에서 사용자 정보 요청하기
     * 2. code 방식과 동일
     * 3. jwt(스프링서버) 생성해서 엡에게 전달
     */
    // kakaoLogin
    @Transactional
    public User kakaoLogin(String code) {

        // 1.1 RestTemplate 설정
        RestTemplate rt = new RestTemplate();

        // 1.2 http header 설정
        HttpHeaders headersForToken = new HttpHeaders();
        headersForToken.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 1.3 http body 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "ade1e7fe23c3912374047ed33db5eff7");
        body.add("redirect_uri", "http://localhost:8080/oauth/kakao/callback");
        body.add("code", code);

        // 1.4 body+header 객체 만들기
        HttpEntity<MultiValueMap<String, String>> requestForToken =
                new HttpEntity<>(body, headersForToken);

        // 1.5 api 요청하기 (토큰 받기)
        ResponseEntity<KakaoResponse.TokenDTO> tokenResponse = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                requestForToken,
                KakaoResponse.TokenDTO.class);


        // 2. 토큰으로 사용자 정보 받기 (PK, Email)
        HttpHeaders headersForUser = new HttpHeaders();
        headersForUser.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headersForUser.add("Authorization", "Bearer " + tokenResponse.getBody().getAccessToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headersForUser);

        ResponseEntity<KakaoResponse.KakaoUserDTO> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request,
                KakaoResponse.KakaoUserDTO.class);

        // 3. 해당정보로 DB조회 (있을수, 없을수)
        String nickname = "kakao_" + response.getBody().getId();
        User userPS = userRepository.findByNickname(nickname).orElse(null);


        // 4. 있으면? - 조회된 유저정보 리턴
        if (userPS != null) {
            saveSessionToRedis("sessionUser", userPS);
            return userPS;
        } else {
            // 5. 없으면? - 강제 회원가입
            User user = User.builder()
                    .nickname(nickname)
                    .password(UUID.randomUUID().toString())
                    .email(response.getBody().getProperties().getNickname() + "@ohflix.com")
                    .provider("kakao")
                    .status(Status.USER)
                    .build();
            User returnUser = userRepository.save(user);
            saveSessionToRedis("sessionUser", returnUser);
            return returnUser;
        }
    }

    private void saveSessionToRedis(String sessionKey, User user) {
        SessionUser sessionUser = new SessionUser(user);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(sessionKey, sessionUser, 1, TimeUnit.HOURS); // 세션 유효기간 1시간
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

    public UserResponse.ProfileFormDTO userProfileForm(Integer sessionUserId) {
        User userProfile = userRepository.findUserProfileById(sessionUserId);
        System.out.println(userProfile);
        return new UserResponse.ProfileFormDTO(userProfile);
    }

    //profile-setting 프로필 세팅 페이지
    public UserResponse.ProfileSettingDTO profileSetting(int userId) {
        User user = userRepository.findUsernameAndIcon(userId).orElseThrow(() -> new Exception404("유저 정보가 없습니다."));
        UserResponse.ProfileSettingDTO respDTO = new UserResponse.ProfileSettingDTO(user);

        return respDTO;
    }

    // 멤버쉽 취소 페이지
    public UserResponse.CancelPlanPageDTO userCancelPlan(Integer sessionUserId) {
        // 유저 아이콘 찾기
        User user = userRepository.findUsernameAndIcon(sessionUserId).orElseThrow(() -> new Exception404("사용자 정보를 찾을 수 없습니다."));

        // 결제 내역 ( list ) 찾기
        List<PurchaseHistory> purchaseHistoryList = purchaseHistoryRepository.findByUser(sessionUserId);
        // 최초 결제 내역과 최근 결제 내역
        PurchaseHistory oldestPurchaseHistory = null;
        PurchaseHistory latestPurchaseHistory = null;
        if (!purchaseHistoryList.isEmpty()) {
            oldestPurchaseHistory = purchaseHistoryList.get(0); // first
            latestPurchaseHistory = purchaseHistoryList.get(purchaseHistoryList.size() - 1); // last
        }

        // 최신 콘텐츠 가져오기
        List<Content> latestContentList = contentRepository.findLatestContent();
        // 12개의 콘텐츠 저장
        if (latestContentList.size() > 12) {
            latestContentList = latestContentList.subList(0, 12);
        }

        return new UserResponse.CancelPlanPageDTO(user, oldestPurchaseHistory, latestPurchaseHistory, latestContentList);
    }

    // 멤버십 상세정보 페이지
    public UserResponse.AccountMembershipDTO accountMembership(Integer sessionUserId) {
        // 유저 아이콘
        User user = userRepository.findUsernameAndIcon(sessionUserId).orElseThrow(() -> new Exception404("사용자 정보를 찾을 수 없습니다."));

        // 결제 내역 찾기
        PurchaseHistory purchaseHistory = purchaseHistoryRepository.findById(sessionUserId).orElseThrow(() -> new Exception404("사용자 정보를 찾을 수 없습니다."));

        // 카드 정보 찾기
        CardInfo cardInfo = cardInfoRepository.findUserInfo(sessionUserId).orElseThrow(() -> new Exception404("사용자 정보를 찾을 수 없습니다."));

        return new UserResponse.AccountMembershipDTO(user, purchaseHistory, cardInfo);
    }

    // sales-page
    public List<UserResponse.SalesPageDTO> salesPage() {
        String startDate = Year.now().toString() + "-01-01";
        String currentDate = LocalDateTime.now().toString();

        List<UserResponse.SalesPageUserDTO> userStats = userNativeRepository.findMonthlyUserStats(startDate, currentDate);
        List<UserResponse.SalesPageSubscribeUserDTO> subscribeUserStats = userNativeRepository.findSubscribeUserStats(startDate, currentDate);
        List<PurchaseHistoryResponse.SalesPageSalesDTO> saleStats = purchaseHistoryNativeRepository.findMonthlySalesStats(startDate, currentDate);


        Map<String, UserResponse.SalesPageUserDTO> userStatsMap = userStats.stream()
                .collect(Collectors.toMap(UserResponse.SalesPageUserDTO::getYearMonth, stat -> stat));
        Map<String, UserResponse.SalesPageSubscribeUserDTO> subscribeUserStatsMap = subscribeUserStats.stream()
                .collect(Collectors.toMap(UserResponse.SalesPageSubscribeUserDTO::getYearMonth, stat -> stat));
        Map<String, PurchaseHistoryResponse.SalesPageSalesDTO> saleStatsMap = saleStats.stream()
                .collect(Collectors.toMap(PurchaseHistoryResponse.SalesPageSalesDTO::getYearMonth, stat -> stat));


        List<UserResponse.SalesPageDTO> respDTO = new ArrayList<>();
        YearMonth startMonth = YearMonth.of(2024, 1);
        YearMonth endMonth = YearMonth.of(2024, 12);

        long cumulativeSales = 0;
        long cumulativeUserCount = 0;

        for (YearMonth month = startMonth; !month.isAfter(endMonth); month = month.plusMonths(1)) {
            String monthString = month.toString();
            UserResponse.SalesPageUserDTO userStat = userStatsMap.getOrDefault(monthString, new UserResponse.SalesPageUserDTO(monthString, 0L, 0L));
            UserResponse.SalesPageSubscribeUserDTO subScribeUserStat = subscribeUserStatsMap.getOrDefault(monthString, new UserResponse.SalesPageSubscribeUserDTO(monthString, 0L));
            PurchaseHistoryResponse.SalesPageSalesDTO saleStat = saleStatsMap.getOrDefault(monthString, new PurchaseHistoryResponse.SalesPageSalesDTO(monthString, 0L));

            cumulativeUserCount += userStat.getMonthlyUserCount();
            cumulativeSales += saleStat.getMonthlySales();


            UserResponse.SalesPageDTO combinedStat = new UserResponse.SalesPageDTO(
                    monthString,
                    subScribeUserStat.getSubscribeUserCount(),
                    cumulativeUserCount,
                    saleStat.getMonthlySales(),
                    cumulativeSales
            );

            respDTO.add(combinedStat);
        }


        return respDTO;
    }

    // 환불 요청 생성
    @Transactional
    public void requestRefund(RefundRequest.RequestDTO reqDTO) {
        PurchaseHistory p = purchaseHistoryRepository.findByUserIdWithRecentInfo(reqDTO.getUserId())
                .orElseThrow(() -> new Exception404("유저를 찾을 수 없습니다."));
        refundRepository.save(Refund.builder()
                .user(p.getUser())
                .reason(reqDTO.getRefundReason())
                .purchasedDate(p.getCreatedAt())
                .status(Refuse.PENDING).build());
    }

    // 환불 요청 목록 불러우기
    public RefundResponse.ListDTO getRefundBoard() {
        List<Refund> refundList = refundRepository.findAll();
        return new RefundResponse.ListDTO(refundList);
    }

    public UserResponse.AccountMembershipInfoDTO accountMembershipInfo(Integer sessionUserId) {

        // 유저 정보 확인
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("사용자 정보를 찾을 수 없습니다."));

        // 결제 내역 찾기
        PurchaseHistory purchaseHistory = purchaseHistoryRepository.findFirstByUserIdOrderByCreatedAtDesc(sessionUserId)
                .orElseThrow(() -> new Exception404("결제 정보를 찾을 수 없습니다."));

        // 카드 정보 찾기
        CardInfo cardInfo = cardInfoRepository.findMainCardInfoByUserId(sessionUserId)
                .orElseThrow(() -> new Exception404("카드 정보를 찾을 수 없습니다."));

        return new UserResponse.AccountMembershipInfoDTO(user, purchaseHistory, cardInfo);
    }

    //login
    public SessionUser login(UserRequest.LoginDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new Exception404("유저 정보가 없습니다."));

        if (!BCrypt.checkpw(requestDTO.getPassword(), user.getPassword())) {
            throw new Exception401("비밀번호가 일치하지 않습니다.");
        }

        return new SessionUser(user);
    }

    // 회원가입 signUp
    @Transactional
    public UserResponse.SignupDTO Signup(UserRequest.SignupDTO reqDTO) {

        String hashedPassword = BCrypt.hashpw(reqDTO.getPassword(), BCrypt.gensalt());

        User user = User.builder()
                .email(reqDTO.getEmail())
                .password(reqDTO.getPassword())
                .nickname(reqDTO.getNickname())
                .status(Status.USER)
                .profileIcon(ProfileIcon.builder().id(1).build())
                .userSaveRate(Rate.ALL)
                .isKids(false)
                .loginSave(false)
                .isAutoPlay(false)
                .isSubscribe(false)
                .build();
        User singupUser = userRepository.save(user);

        return new UserResponse.SignupDTO(singupUser);
    }

    // 비밀번호 변경
    @Transactional
    public void updatePassword(UserRequest.UpdatePasswordDTO reqDTO, Integer sessionUserId) {
        // 조회 및 예외 처리
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("유저를 찾을 수 없습니다."));

        // 권한 처리
        if (sessionUserId != user.getId()) {
            throw new Exception403("비밀번호를 변경할 권한이 없습니다.");
        }

        // 현재 비밀번호 체크
        if (!Objects.equals(reqDTO.getCurrentPassword(), user.getPassword())) {
            throw new Exception400("현재 비밀번호가 틀렸습니다.");
        }

        // 새 비밀번호, 새 비밀번호 동일 체크
        if (!Objects.equals(reqDTO.getNewPassword(), reqDTO.getNewPasswordCheck())) {
            throw new Exception400("새 비밀번호가 일치하지 않습니다.");
        }

        user.updatePassword(reqDTO);

    }
}





