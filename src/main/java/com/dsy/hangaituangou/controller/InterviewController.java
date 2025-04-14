package com.dsy.hangaituangou.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsy.hangaituangou.domain.Interview;
import com.dsy.hangaituangou.domain.bo.InterviewBO;
import com.dsy.hangaituangou.domain.vo.InterviewVO;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.service.InterviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("interview")
@RequiredArgsConstructor
@Tag(name = "面试管理", description = "面试管理")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping("accept")
    public RespBase<Boolean> acceptInterview(@RequestParam("id") String id) {
        return RespBase.success(interviewService.acceptInterview(id));
    }

    @PostMapping("list")
    public RespBase<Page<InterviewVO>> listInterview(@RequestBody InterviewBO interview) {
        return RespBase.success(interviewService.listInterview(interview));
    }

    @PostMapping("edit")
    public RespBase<Boolean> editInterview(@RequestBody Interview interview) {
        return RespBase.success(interviewService.updateById(interview));
    }

    @PostMapping("delete")
    public RespBase<Boolean> deleteInterview(@RequestParam("id") String id) {
        return RespBase.success(interviewService.removeById(id));
    }

}
