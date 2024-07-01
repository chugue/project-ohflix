package com.project.ohflix.domain.like;

import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    // 좋아요 하기
    @Transactional
    public LikeResponse.AddLikeDTO addLike(Integer contentId, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception404("해당하는 사용자를 찾을 수 없습니다."));
        Content content = contentRepository.findById(contentId).orElseThrow(() -> new Exception404("해당하는 컨텐츠를 찾을 수 없습니다."));

        Like like = likeRepository.save(Like.builder()
                .user(user)
                .content(content)
                .build());
        return new LikeResponse.AddLikeDTO(like);
    }

    // 좋아요 취소
    @Transactional
    public LikeResponse.RemoveLikeDTO removeLike(Integer contentId, Integer userId) {
        Optional<Like> like = likeRepository.findByUserIdAndContentId(userId, contentId);
        if (like.isPresent()) {
            likeRepository.delete(like.get());

            return new LikeResponse.RemoveLikeDTO(like.get());
        } else {
            throw new Exception404("해당 좋아요가 존재하지 않습니다.");
        }
    }

    // 좋아요 상태 조회하기
    public LikeResponse.LikeStatus getLikeStatus(Integer contentId, Integer userId) {
        Optional<Like> like = likeRepository.findByUserIdAndContentId(userId, contentId);
        return new LikeResponse.LikeStatus(userId, contentId, like);
    }
}
