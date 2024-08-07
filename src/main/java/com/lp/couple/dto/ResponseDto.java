package com.lp.couple.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto <T>{

    private String status;
    private T data;
    private String message;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>("success", data, null);
    }

    public static <T> ResponseDto<T> success(T data, String message) {
        return new ResponseDto<>("success", data, message);
    }

    public static <T> ResponseDto<T> error(String message) {
        return new ResponseDto<>("error", null, message);
    }
}
