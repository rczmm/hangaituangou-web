package com.dsy.hangaituangou.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum MessageTypeEnum implements IEnum<String> {


    TEXT(1, "文本消息"), INTERVIEW_INVITATION(2, "面试邀请"), FILE(3, "文件");


    private final int code;
    private final String description;

    MessageTypeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.name();
    }
}
