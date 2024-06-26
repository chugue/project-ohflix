package com.project.ohflix.domain.like;

import com.project.ohflix._core.error.exception.Exception400;
import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.mylist.MyList;
import com.project.ohflix.domain.mylist.MyListRequest;
import com.project.ohflix.domain.mylist.MyListResponse;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    // 좋아요 하기
    @Transactional
    public LikeResponse.AddLikeDTO addLike(LikeRequest.AddLikeDTO reqDTO) {
        if (likeRepository.findByUserIdAndContentId(reqDTO.getUserId(), reqDTO.getContentId()).isPresent()) {
            throw new Exception400("이미 좋아요 되어있는 컨텐츠입니다.");
        }

        User user = userRepository.findById(reqDTO.getUserId()).orElseThrow(() -> new Exception404("해당하는 사용자를 찾을 수 없습니다."));
        Content content = contentRepository.findById(reqDTO.getContentId()).orElseThrow(() -> new Exception404("해당하는 컨텐츠를 찾을 수 없습니다."));

        Like like = new Like();
        like.setUser(user);
        like.setContent(content);

        likeRepository.save(like);

        return new LikeResponse.AddLikeDTO(reqDTO);
    }

    // 좋아요 취소
    @Transactional
    public LikeResponse.RemoveLikeDTO removeLike(LikeRequest.RemoveLikeDTO reqDTO) {
        Optional<Like> like = likeRepository.findByUserIdAndContentId(reqDTO.getUserId(), reqDTO.getContentId());
        if (like.isPresent()) {
            likeRepository.delete(like.get());

            return new LikeResponse.RemoveLikeDTO(reqDTO);
        } else {
            throw new Exception404("해당 좋아요가 존재하지 않습니다.");
        }
    }
}
