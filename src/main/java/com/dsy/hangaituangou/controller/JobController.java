package com.dsy.hangaituangou.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsy.hangaituangou.domain.bo.JobBO;
import com.dsy.hangaituangou.domain.vo.JobVO;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/job")
@RequiredArgsConstructor
@Tag(name = "岗位管理", description = "岗位接口")
public class JobController {

    private final JobService jobService;


    @Operation(summary = "获取岗位列表", description = "获取岗位列表 分页 条件查询")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "获取岗位列表成功"),
                    @ApiResponse(responseCode = "400", description = "获取岗位列表失败")
            }
    )
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RespBase<Page<JobVO>> listByParams(@RequestBody JobBO jobBO) {
        return RespBase.success(jobService.listByParams(jobBO));
    }
}
