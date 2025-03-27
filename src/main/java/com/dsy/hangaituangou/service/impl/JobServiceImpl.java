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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

 /**
 * 岗位服务实现类
 */
@Service
@RequiredArgsConstructor
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    private final Gson gson;

    @Override
    public Page<JobVO> listByParams(JobBO jobBO) {

        Page<Job> jobPage = page(new Page<>(jobBO.getPageNum(), jobBO.getPageSize()),
                new LambdaQueryWrapper<Job>()
                        .like(Objects.nonNull(jobBO.getType()), Job::getTags, jobBO.getType())
                        .and(Objects.nonNull(jobBO.getKeyword()) && !jobBO.getKeyword().isEmpty(), o -> o.like(Objects.nonNull(jobBO.getKeyword()),
                                        Job::getTitle,
                                        jobBO.getKeyword())
                                .or()
                                .like(Objects.nonNull(jobBO.getKeyword()), Job::getCompany, jobBO.getKeyword())
                                .or()
                                .like(Objects.nonNull(jobBO.getKeyword()), Job::getTags, jobBO.getKeyword()))
                        .orderByDesc(Job::getCreateTime)
        );

        if (jobPage != null) {
            Page<JobVO> jobVOPage = new Page<>(jobPage.getCurrent(), jobPage.getSize(), jobPage.getTotal());
            jobVOPage.setRecords(jobPage.getRecords().stream().map(job -> {
                        List<String> tags = gson.fromJson(job.getTags(), new TypeToken<List<String>>() {
                        }.getType());
                        return JobVO.builder()
                                .id(job.getId().toString())
                                .title(job.getTitle())
                                .salary(job.getSalary())
                                .company(job.getCompany())
                                .companySize(job.getCompanySize())
                                .companyLogo(job.getCompanyLogo())
                                .tags(tags)
                                .hrName(job.getHrName())
                                .location(job.getLocation())
                                .workExperience(job.getWorkExperience())
                                .education(job.getEducation())
                                .benefits(Arrays.stream(job.getBenefits()
                                        .replaceAll("\\[", "")
                                        .replaceAll("]", "")
                                        .replaceAll(" ", "")
                                        .replaceAll("\"", "").split(",")).toList())
                                .description(job.getDescription())
                                .requirements(Arrays.stream(job.getRequirements()
                                        .replaceAll("\\[", "")
                                        .replaceAll("]", "")
                                        .replaceAll(" ", "")
                                        .replaceAll("\"", "").split(",")).toList())
                                .status(job.getStatus())
                                .date(job.getDate())
                                .interviewTime(job.getInterviewTime())
                                .isFavorite(job.getIsFavorite())
                                .build();
                    }
            ).toList());
            jobVOPage.setCurrent(jobPage.getCurrent());
            jobVOPage.setSize(jobPage.getSize());
            jobVOPage.setTotal(jobPage.getTotal());
            return jobVOPage;
        }

        throw new BusinessException("岗位列表为空！");
    }

    @Override
    public Object getTags() {
        Set<String> res = new HashSet<>();
        for (Job job : list()) {
            List<String> tags = gson.fromJson(job.getTags(), new TypeToken<List<String>>() {
            }.getType());
            res.addAll(tags);
        }
        return res;
    }
}