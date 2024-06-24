package com.project.ohflix._core.utils;


import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class RedisUtil {
    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);
    private final HttpSession session;
    private final RedisTemplate<String, Object> redisTemplate;

    public void saveSessionUser(SessionUser sessionUser){
        redisTemplate.opsForValue().set("sessionUser", sessionUser);
        session.setAttribute("sessionUser", sessionUser);
    }

    public SessionUser getSessionUser() {
        SessionUser sessionUser=(SessionUser) redisTemplate.opsForValue().get("sessionUser");
        log.info("user : "+sessionUser.toString());
        return sessionUser;
    }

    public void deleteSessionUser() {
        redisTemplate.delete(session.getId());
        session.invalidate();

    }
}
