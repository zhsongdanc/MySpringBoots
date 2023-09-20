package com.example.event.dto;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/2/3 13:24
 */

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResultReqDTO<T> {

    private String msg;
    private boolean success = true;
    private T data;
    private int code;

    // success
    private ResultReqDTO(T data) {
        this.data = data;
    }


    // false
    private ResultReqDTO(int code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResultReqDTO<T> success(T data) {
        return new ResultReqDTO<T>(data);
    }

    public static <T> ResultReqDTO<T> fail(int code, String msg) {
        return new ResultReqDTO<>(code, msg);
    }

}
