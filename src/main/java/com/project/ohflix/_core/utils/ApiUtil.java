package com.project.ohflix._core.utils;

import lombok.Data;

@Data
public class ApiUtil<T> {
    private Integer status;
    private Boolean success;
    private T response;
    private String errorMessage;

    // 성공시
    public ApiUtil(T response) {
        this.status = 200;
        this.success = true;
        this.response = response;
        this.errorMessage = null;
    }

    // 실패시
    public ApiUtil(Integer status, String errorMessage) {
        this.status = status;
        this.success = false;
        this.response = null;
        this.errorMessage = errorMessage;
    }
}
