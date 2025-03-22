package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.Job;
import com.dsy.hangaituangou.domain.bo.JobBO;
import com.dsy.hangaituangou.domain.vo.JobVO;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.dsy.hangaituangou.mapper.JobMapper;
import com.dsy.hangaituangou.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * 岗位服务实现类
 */
@Service
@RequiredArgsConstructor
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Override
    public Page<JobVO> listByParams(JobBO jobBO) {

        Page<Job> jobPage = page(new Page<>(jobBO.getPageNum(), jobBO.getPageSize()),
                new LambdaQueryWrapper<Job>()
                        .like(Objects.nonNull(jobBO.getTitle()), Job::getTitle, jobBO.getTitle())
                        .like(Objects.nonNull(jobBO.getCompany()), Job::getCompany, jobBO.getCompany())
                        .like(Objects.nonNull(jobBO.getTag()), Job::getTags, jobBO.getTag()));

        if (jobPage != null) {
            Page<JobVO> jobVOPage = new Page<>(jobPage.getCurrent(), jobPage.getSize(), jobPage.getTotal());
            jobVOPage.setRecords(jobPage.getRecords().stream().map(job -> JobVO.builder()
                    .id(job.getId().toString())
                    .title(job.getTitle())
                    .salary(job.getSalary())
                    .company(job.getCompany())
                    .companySize(job.getCompanySize())
                    .companyLogo(job.getCompanyLogo())
                    .tags(Arrays.stream(job.getTags().split(",")).toList())
                    .hrName(job.getHrName())
                    .location(job.getLocation())
                    .workExperience(job.getWorkExperience())
                    .education(job.getEducation())
                    .benefits(Arrays.stream(job.getBenefits().split(",")).toList())
                    .description(job.getDescription())
                    .requirements(Arrays.stream(job.getRequirements().split(",")).toList())
                    .status(job.getStatus())
                    .date(job.getDate())
                    .interviewTime(job.getInterviewTime())
                    .isFavorite(job.getIsFavorite())
                    .build()).toList());
            return jobVOPage;
        }

        throw new BusinessException("岗位列表为空！");
    }
}