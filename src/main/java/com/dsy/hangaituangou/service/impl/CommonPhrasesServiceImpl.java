package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.CommonPhrases;
import com.dsy.hangaituangou.domain.bo.CommonPhrasesBO;
import com.dsy.hangaituangou.domain.vo.CommonPhrasesVO;
import com.dsy.hangaituangou.mapper.CommonPhrasesMapper;
import com.dsy.hangaituangou.service.CommonPhrasesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonPhrasesServiceImpl extends ServiceImpl<CommonPhrasesMapper, CommonPhrases> implements CommonPhrasesService {

    @Override
    public List<CommonPhrasesVO> getList(String userId) {
        return list(new LambdaQueryWrapper<CommonPhrases>()
                .eq(CommonPhrases::getUserId, userId))
                .stream()
                .map(commonPhrases -> CommonPhrasesVO.builder()
                        .id(commonPhrases.getId().toString())
                        .text(commonPhrases.getPhraseText())
                        .build())
                .toList();
    }

    @Override
    public Boolean add(CommonPhrasesBO commonPhrasesBO) {
        return save(CommonPhrases.builder()
                .phraseText(commonPhrasesBO.getText())
                .userId(commonPhrasesBO.getId())
                .build());
    }

    @Override
    public Boolean edit(CommonPhrasesBO commonPhrasesBO) {
        CommonPhrases commonPhrases = getById(commonPhrasesBO.getId());
        if (commonPhrases == null) {
            return false;
        }
        commonPhrases.setPhraseText(commonPhrasesBO.getText());
        return updateById(commonPhrases);
    }
}
