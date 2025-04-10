package com.dsy.hangaituangou.controller;

import com.dsy.hangaituangou.domain.UserResume;
import com.dsy.hangaituangou.domain.bo.UserResumeBO;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.service.UserResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/resume")
@RequiredArgsConstructor
@Tag(name = "简历管理", description = "简历接口")
public class UserResumeController {

    private final UserResumeService userResumeService;

    @Operation(summary = "保存简历", description = "新增或修改简历")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "操作成功"),
                    @ApiResponse(responseCode = "400", description = "操作失败")
            }
    )
    @PostMapping("/save")
    public RespBase<UserResume> saveResume(@RequestBody UserResumeBO resumeBO) {
        UserResume resume;
        if (resumeBO.getId() != null) {
            resume = userResumeService.updateResume(resumeBO.getId(), resumeBO);
        } else {
            resume = userResumeService.createResume(resumeBO);
        }
        return RespBase.success(resume);
    }

    @DeleteMapping("/{id}")
    public RespBase<Void> deleteResume(@PathVariable Long id) {
        userResumeService.deleteResume(id);
        return RespBase.success();
    }

    @GetMapping("/{id}")
    public RespBase<UserResume> getResumeById(@PathVariable Long id) {
        UserResume resume = userResumeService.getResumeById(id);
        return RespBase.success(resume);
    }

    @GetMapping("/user")
    public RespBase<List<UserResume>> getResumesByUserId(@RequestParam Long userId) {
        List<UserResume> resumes = userResumeService.getResumesByUserId(userId);
        return RespBase.success(resumes);
    }
}