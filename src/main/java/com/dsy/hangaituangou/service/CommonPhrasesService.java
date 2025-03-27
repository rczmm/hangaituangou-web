package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.CommonPhrases;
import com.dsy.hangaituangou.domain.bo.CommonPhrasesBO;
import com.dsy.hangaituangou.domain.vo.CommonPhrasesVO;

import java.util.List;

public interface CommonPhrasesService extends IService<CommonPhrases> {
    List<CommonPhrasesVO> getList(String userId);

    Boolean add(CommonPhrasesBO commonPhrasesBO);

    Boolean edit(CommonPhrasesBO commonPhrasesBO);
}
