package com.dsy.hangaituangou.controller;

import com.dsy.hangaituangou.domain.bo.CommonPhrasesBO;
import com.dsy.hangaituangou.domain.vo.CommonPhrasesVO;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.service.CommonPhrasesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/commonPhrases")
@RequiredArgsConstructor
@Tag(name = "常用语管理", description = "常用语接口")
public class CommonPhrasesController {

    private final CommonPhrasesService commonPhrasesService;

    @Operation(summary = "获取常用语列表", description = "获取常用语列表 条件查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBase<List<CommonPhrasesVO>> list(@RequestParam String userId) {
        return RespBase.success(commonPhrasesService.getList(userId));
    }

    @Operation(summary = "添加常用语", description = "添加常用语")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBase<Boolean> add(@RequestBody CommonPhrasesBO commonPhrasesBO) {
        return RespBase.success(commonPhrasesService.add(commonPhrasesBO));
    }

    @Operation(summary = "编辑常用语", description = "编辑常用语")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RespBase<Boolean> edit(@RequestBody CommonPhrasesBO commonPhrasesBO) {
        return RespBase.success(commonPhrasesService.edit(commonPhrasesBO));
    }
}
