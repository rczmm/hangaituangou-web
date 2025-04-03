package com.dsy.hangaituangou.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsy.hangaituangou.domain.Job;
import com.dsy.hangaituangou.domain.bo.IdsBO;
import com.dsy.hangaituangou.domain.bo.JobAddBO;
import com.dsy.hangaituangou.domain.bo.JobBO;
import com.dsy.hangaituangou.domain.vo.JobVO;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.enums.JobStatusEnum;
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

    @Operation(summary = "获取所有岗位标签", description = "获取所有岗位标签")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "获取所有岗位标签成功"),
                    @ApiResponse(responseCode = "400", description = "获取所有岗位标签失败")
            }
    )
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public RespBase<Object> getTags() {
        return RespBase.success(jobService.getTags());
    }

    @Operation(summary = "新增岗位", description = "新增岗位")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "新增岗位成功"),
                    @ApiResponse(responseCode = "400", description = "新增岗位失败")
            }
    )
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBase<Boolean> add(@RequestBody JobAddBO jobAddBO) {
        return RespBase.success(jobService.add(jobAddBO));
    }

    @Operation(summary = "修改岗位", description = "修改岗位")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "修改岗位成功"),
                    @ApiResponse(responseCode = "400", description = "修改岗位失败")
            }
    )
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBase<Boolean> edit(@RequestBody JobAddBO jobAddBO) {
        return RespBase.success(jobService.edit(jobAddBO));
    }

    @Operation(summary = "删除岗位", description = "删除岗位")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "删除岗位成功"),
                    @ApiResponse(responseCode = "400", description = "删除岗位失败")
            }
    )
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RespBase<Boolean> delete(@RequestBody IdsBO idsBO) {
        return RespBase.success(jobService.removeByIds(idsBO.getIds()));
    }

    @Operation(summary = "停用岗位", description = "停用岗位")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "停用岗位成功"),
                    @ApiResponse(responseCode = "400", description = "停用岗位失败")
            }
    )
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public RespBase<Boolean> disable(@RequestBody IdsBO idsBO) {
        return RespBase.success(jobService.updateBatchById(idsBO.getIds().stream().map(id -> {
            Job job = new Job();
            job.setId(Long.valueOf(id));
            job.setStatus(JobStatusEnum.CLOSE);
            return job;
        }).toList()));
    }
}
