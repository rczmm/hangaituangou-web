package com.dsy.hangaituangou.domain.vo.base;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serializable;

@Data
@AllArgsConstructor
public class RespBase<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;
    public static final int UNAUTHORIZED_CODE = 401;//未认证
    public static final int FORBIDDEN_CODE = 403; //没有权限


    private int code;
    private String msg;
    private T data;

    // 编写成功和失败的方法
    // 静态成功方法
    public static <T> RespBase<T> success() {
        return new RespBase<>(SUCCESS_CODE, "操作成功", null);
    }

    public static <T> RespBase<T> success(T data) {
        return new RespBase<>(SUCCESS_CODE, "操作成功", data);
    }

    public static <T> RespBase<T> success(String msg, T data) {
        return new RespBase<>(SUCCESS_CODE, msg, data);
    }

    // 静态失败方法
    public static <T> RespBase<T> fail() {
        return new RespBase<>(FAIL_CODE, "操作失败", null);
    }

    public static <T> RespBase<T> fail(String msg) {
        return new RespBase<>(FAIL_CODE, msg, null);
    }

    public static <T> RespBase<T> fail(int code, String msg) {
        return new RespBase<>(code, msg, null);
    }

    public static <T> RespBase<T> fail(int code, String msg, T data) {
        return new RespBase<>(code, msg, data);
    }

    //未认证
    public static <T> RespBase<T> unauthorized(String msg) {
        return new RespBase<>(UNAUTHORIZED_CODE, msg, null);
    }

    //没有权限
    public static <T> RespBase<T> forbidden(String msg) {
        return new RespBase<>(FORBIDDEN_CODE, msg, null);
    }

    public boolean isSuccess() {
        return this.code == SUCCESS_CODE;
    }


}
