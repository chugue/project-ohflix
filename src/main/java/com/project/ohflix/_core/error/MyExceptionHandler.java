package com.project.ohflix._core.error;

import com.project.ohflix._core.error.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

    @Slf4j
    @ControllerAdvice
    public class MyExceptionHandler {

        @ExceptionHandler(Exception400.class)
        public String ex400(Exception400 e, HttpServletRequest request){
            request.setAttribute("msg", e.getMessage());
            log.warn("400 : " + e.getMessage());
            return "err/400";
        }

        @ExceptionHandler(Exception401.class)
        public String ex401(Exception401 e, HttpServletRequest request){
            request.setAttribute("msg", e.getMessage());
            log.warn("401 : " + e.getMessage());
            log.warn("IP : " + request.getRemoteAddr());
            log.warn("WAY : " + request.getHeader("User-Agent"));
            return "err/401";
        }

        @ExceptionHandler(Exception403.class)
        public String ex403(Exception403 e, HttpServletRequest request){
            request.setAttribute("msg", e.getMessage());
            log.warn("403 : " + e.getMessage());
            return "err/403";
        }

        @ExceptionHandler(Exception404.class)
        public String ex404(Exception404 e, HttpServletRequest request){
            request.setAttribute("msg", e.getMessage());
            log.info("404 : " + e.getMessage());
            return "/err/404";
        }

        @ExceptionHandler(Exception500.class)
        public String ex500(RuntimeException e, HttpServletRequest request){
            request.setAttribute("msg", e.getMessage());
            log.error("500 : " + e.getMessage());
            return "err/500";
        }
    }
