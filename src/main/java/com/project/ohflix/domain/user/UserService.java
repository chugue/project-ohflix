package com.project.ohflix.domain.user;

import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.profileIcon.ProfileIconRepository;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistoryRepository;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistoryNativeRepository;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public UserResponse.CancelPlanPageDTO userCanclePlan(Integer sessionUserId) {
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
    public SessionUser login(UserRequest.LoginDTO reqestDTO) {
        User user = userRepository.findByEmailAndPassword(reqestDTO.getEmail(), reqestDTO.getPassword()).orElseThrow(() -> new Exception404("유저 정보가 없습니다."));

        return new SessionUser(user.getId(), user.getStatus());
    }
}





