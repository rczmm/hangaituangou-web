package com.dsy.hangaituangou.domain.base;

import lombok.Data;

@Data
public class PageBase {

    private Long pageNum = 1L;

    private Long pageSize = 10L;

}
