package com.dsy.hangaituangou.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum JobStatusEnum implements IEnum<String> {

    OPEN(1, "招聘中"),
    CLOSE(2, "已关闭"),
    FULL(3, "已满员");

    private final int code;
    private final String desc;

    JobStatusEnum(int code, String status) {
        this.code = code;
        this.desc = status;
    }

    @Override
    public String getValue() {
        return this.name();
    }
}
