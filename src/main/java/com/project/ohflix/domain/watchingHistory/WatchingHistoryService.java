package com.project.ohflix.domain.watchingHistory;

import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchingHistoryService {

    private final UserRepository userRepository;
    private final WatchingHistoryRepository watchingHistoryRepository;

    public WatchingHistoryResponse.WatchingHistoryDTO getWatchingHistory(Integer sessoionUserId) {
        User user = userRepository.findById(sessoionUserId).orElseThrow(() -> new Exception404("해당하는 사용자를 찾을 수 없습니다."));
        List<WatchingHistory> watchingHistories = watchingHistoryRepository.findByUserId(user.getId());
        WatchingHistoryResponse.WatchingHistoryDTO responseDTO = new WatchingHistoryResponse.WatchingHistoryDTO(user, watchingHistories);

        return responseDTO;
    }
}





