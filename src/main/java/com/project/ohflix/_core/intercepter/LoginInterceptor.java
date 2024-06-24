package com.project.ohflix._core.intercepter;

import com.project.ohflix._core.error.exception.Exception401;
import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;


@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        HttpSession session=request.getSession();

        SessionUser sessionUser=(SessionUser) session.getAttribute("sessionUser");
        if(sessionUser == null){
            throw new Exception401("로그인 하셔야 해요");
        }
        return true;
    }
}
