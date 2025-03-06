package com.dsy.hangaituangou.domain.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "统一响应包装类")
public class RespBase<T> {

    @Schema(description = "响应码", example = "200")
    private Integer code;

    @Schema(description = "响应消息", example = "操作成功")
    private String message;

    @Schema(description = "响应数据")
    private T data;

    public static <T> RespBase<T> success(T data) {
        RespBase<T> respBase = new RespBase<>();
        respBase.setCode(200);
        respBase.setMessage("操作成功");
        respBase.setData(data);
        return respBase;
    }

    public static <T> RespBase<T> error(String message) {
        RespBase<T> respBase = new RespBase<>();
        respBase.setCode(500);
        respBase.setMessage(message);
        return respBase;
    }

    public static <T> RespBase<T> error(Integer code, String message) {
        RespBase<T> respBase = new RespBase<>();
        respBase.setCode(code);
        respBase.setMessage(message);
        return respBase;
    }
}