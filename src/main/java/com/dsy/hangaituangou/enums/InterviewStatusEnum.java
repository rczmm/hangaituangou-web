package com.dsy.hangaituangou.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum InterviewStatusEnum implements IEnum<String> {

    // 待接收
    PENDING_RECEIPT(0, "待接收"),

    PENDING(1, "待面试"),
    PASSED(2, "面试通过"),
    CANCELLED(3, "面试取消"),
    FAILED(4, "面试失败");

    private final int code;
    private final String description;

    InterviewStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.name();
    }
}
