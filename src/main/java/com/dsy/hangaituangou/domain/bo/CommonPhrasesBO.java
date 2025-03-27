package com.dsy.hangaituangou.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "常用语BO", example = "{\"id\": \"1\", \"text\": \"你好\"}")
public class CommonPhrasesBO {

    @Schema(description = "常用语id", example = "1")
    @NotNull(message = "常用语id不能为空")
    private String id;

    @Schema(description = "常用语文本", example = "你好")
    @NotNull(message = "常用语文本不能为空")
    private String text;

}
