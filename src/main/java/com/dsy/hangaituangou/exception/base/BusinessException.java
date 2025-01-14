package com.dsy.hangaituangou.exception.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    // 可选：用于表示业务错误类型的枚举或简单字符串
    private String errorCode;


    // 带有错误信息的构造函数
    public BusinessException(String message) {
        super(message);
    }

    // 带有错误信息和原因的构造函数
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    // 带有错误信息、原因和错误代码的构造函数
    public BusinessException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    // 带有错误信息和错误代码的构造函数
    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}

