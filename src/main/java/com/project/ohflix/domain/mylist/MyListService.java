package com.project.ohflix.domain.mylist;

import com.project.ohflix._core.error.exception.Exception400;
import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.content.ContentRequest;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import com.project.ohflix.domain.watchingHistory.WatchingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyListService {

    private final MyListRepository myListRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final WatchingHistoryRepository watchingHistoryRepository;

    @Transactional
    public MyListResponse.MyListDTO findMyListById(Integer sessionUserId) {
        // 헤더 유저 가져오기 ( 프로필 아이콘 )
        User user = userRepository.findUserProfileById(sessionUserId);

        List<MyList> myFavoriteList = myListRepository.findMyListByUserId(sessionUserId);
        List<MyList> myWatchList = myListRepository.findMyWatchListByUserId(sessionUserId);

        return new MyListResponse.MyListDTO(user, myFavoriteList, myWatchList);
    }

    // 찜 기능
    @Transactional
    public MyListResponse.AddFavoriteDTO addFavorite(MyListRequest.AddFavoriteDTO reqDTO) {
        if (myListRepository.findByUserIdAndContentId(reqDTO.getUserId(), reqDTO.getContentId()).isPresent()) {
            throw new Exception400("이미 찜 되어있는 컨텐츠입니다.");
        }

        User user = userRepository.findById(reqDTO.getUserId()).orElseThrow(() -> new Exception404("해당하는 사용자를 찾을 수 없습니다."));
        Content content = contentRepository.findById(reqDTO.getContentId()).orElseThrow(() -> new Exception404("해당하는 컨텐츠를 찾을 수 없습니다."));

        MyList myList = new MyList();
        myList.setUser(user);
        myList.setContent(content);
        myList.setWatchOrFav(WatchOrFav.FAVORITE);
        myListRepository.save(myList);

        boolean isFavorite = true;
        isFavorite(isFavorite);

        return new MyListResponse.AddFavoriteDTO(reqDTO);
    }

    // 찜 취소 기능
    @Transactional
    public MyListResponse.RemoveFavoriteDTO removeFavorite(MyListRequest.RemoveFavoriteDTO reqDTO) {
        Optional<MyList> favorite = myListRepository.findByUserIdAndContentId(reqDTO.getUserId(), reqDTO.getContentId());
        if (favorite.isPresent()) {
            myListRepository.delete(favorite.get());
            boolean isFavorite = false;
            isFavorite(isFavorite);
            return new MyListResponse.RemoveFavoriteDTO(reqDTO);
        } else {
            throw new Exception404("해당 컨텐츠가 존재하지 않습니다.");
        }
    }

    // 찜 여부
    public MyListResponse.FavoriteCheck isFavorite(boolean value) {
        boolean isFavorite = value;

        return new MyListResponse.FavoriteCheck(isFavorite);
    }

    //영화 시청한 시간 저장하기
    @Transactional
    public void savePlayedTime(ContentRequest.VideoProgressDTO videoProgressDTO, Integer sessionUserId) {
        User user = userRepository.findById(sessionUserId).orElseThrow(() -> new Exception404("해당하는 사용자를 찾을 수 없습니다."));
        Content content = contentRepository.findByVideoPath(videoProgressDTO.getFilename()).orElseThrow(() -> new Exception404("해당하는 컨텐츠를 찾을 수 없습니다."));
        MyList myList = myListRepository.findMyListByUserIdAndContentIdAndWatch(user.getId(), content.getId()).orElse(null);
        System.out.println("myList = " + myList);

        //시청기록 저장
        watchingHistoryRepository.save(videoProgressDTO.toWatcingHistoryEntity(user, content, videoProgressDTO));

        if (myList != null){
            myListRepository.deleteByUserIdAndContentId(user.getId(), content.getId());
            myListRepository.save(videoProgressDTO.toMyListEntity(user, content, videoProgressDTO));
        }else {
            myListRepository.save(videoProgressDTO.toMyListEntity(user, content, videoProgressDTO));
        }
    }

    //영화 시청한 시간 가져오기
    @Transactional
    public Double getPlayedTime(Integer sessionUserId, String filename) {
        User user = userRepository.findById(sessionUserId).orElseThrow(() -> new Exception404("해당하는 사용자를 찾을 수 없습니다."));
        Content content = contentRepository.findByVideoPath(filename).orElseThrow(() -> new Exception404("해당하는 컨텐츠를 찾을 수 없습니다."));
        MyList myList = myListRepository.findMyListByUserIdAndContentIdAndWatch(user.getId(), content.getId()).orElse(null);
        System.out.println("myList = " + myList);
        if (myList != null){
            return myList.getPlayedTime();
        }else {
            return 0.0;
        }
    }
}





