package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.Company;
import com.dsy.hangaituangou.domain.Job;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.bo.JobAddBO;
import com.dsy.hangaituangou.domain.bo.JobBO;
import com.dsy.hangaituangou.domain.vo.JobVO;
import com.dsy.hangaituangou.enums.JobStatusEnum;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.dsy.hangaituangou.mapper.CompanyMapper;
import com.dsy.hangaituangou.mapper.JobMapper;
import com.dsy.hangaituangou.service.JobService;
import com.dsy.hangaituangou.service.SysUserService;
import com.dsy.hangaituangou.utils.SecurityUtils;
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

    private final SysUserService sysUserService;

    private final CompanyMapper  companyMapper;

    @Override
    public Page<JobVO> listByParams(JobBO jobBO) {

        SysUser currentUser = sysUserService.getById(SecurityUtils.getCurrentUserId());

        Page<Job> jobPage = page(new Page<>(jobBO.getPageNum(), jobBO.getPageSize()),
                new LambdaQueryWrapper<Job>()
                        .like(Objects.nonNull(jobBO.getType()), Job::getTags, jobBO.getType())
                        .and(Objects.nonNull(jobBO.getKeyword()) && !jobBO.getKeyword().isEmpty(), o -> o.like(Objects.nonNull(jobBO.getKeyword()), Job::getTitle, jobBO.getKeyword())
                                .or()
                                .like(Objects.nonNull(jobBO.getKeyword()), Job::getCompany, jobBO.getKeyword())
                                .or()
                                .like(Objects.nonNull(jobBO.getKeyword()), Job::getTags, jobBO.getKeyword()))
                        .like(Objects.nonNull(jobBO.getTitle()), Job::getTitle, jobBO.getTitle())
                        .like(Objects.nonNull(jobBO.getLocation()), Job::getLocation, jobBO.getLocation())
                        .eq(Objects.nonNull(jobBO.getMinSalary()), Job::getMinSalary, jobBO.getMinSalary())
                        .eq(Objects.nonNull(jobBO.getMaxSalary()), Job::getMaxSalary, jobBO.getMaxSalary())
                        .eq(Objects.nonNull(jobBO.getCategory()), Job::getCategory, jobBO.getCategory())
                        .eq(currentUser.getUserType().equals(0), Job::getHrUserId, SecurityUtils.getCurrentUserId())
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
                                .hrUserId(job.getHrUserId())
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
                                .status(job.getStatus().getDesc())
                                .date(job.getDate())
                                .interviewTime(job.getInterviewTime())
                                .isFavorite(job.getIsFavorite())
                                .category(job.getCategory())
                                .count(job.getCount())
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

    @Override
    public Boolean add(JobAddBO jobAddBO) {

        SysUser currentUser = sysUserService.getById(SecurityUtils.getCurrentUserId());

        Company currentCompany = companyMapper.selectById(currentUser.getCompanyId());

        return save(Job.builder()
                .title(jobAddBO.getTitle())
                .minSalary(jobAddBO.getMinSalary())
                .maxSalary(jobAddBO.getMaxSalary())
                .salary(jobAddBO.getSalary())
                .tags(gson.toJson(jobAddBO.getTags()))
                .hrUserId(currentUser.getId().toString())
                .location(jobAddBO.getLocation())
                .workExperience(jobAddBO.getWorkExperience())
                .education(jobAddBO.getEducation())
                .benefits(gson.toJson(jobAddBO.getBenefits()))
                .description(jobAddBO.getDescription())
                .requirements(gson.toJson(jobAddBO.getRequirements()))
                .date(jobAddBO.getDate())
                .status(JobStatusEnum.OPEN)
                .company(currentCompany.getName())
                .companySize(currentCompany.getScale())
                .companyLogo(currentCompany.getLogoUrl())
                .hrName(currentUser.getUsername())
                .category(jobAddBO.getCategory())
                .count(jobAddBO.getCount())
                .build()
        );
    }

    @Override
    public Boolean edit(JobAddBO jobAddBO) {

        Job job = getById(jobAddBO.getId());
        if (job == null) {
            return false;
        }
        job.setTitle(jobAddBO.getTitle());
        job.setMinSalary(jobAddBO.getMinSalary());
        job.setMaxSalary(jobAddBO.getMaxSalary());
        job.setSalary(jobAddBO.getSalary());
        job.setTags(gson.toJson(jobAddBO.getTags()));
        job.setLocation(jobAddBO.getLocation());
        job.setWorkExperience(jobAddBO.getWorkExperience());
        job.setEducation(jobAddBO.getEducation());
        job.setBenefits(gson.toJson(jobAddBO.getBenefits()));
        job.setDescription(jobAddBO.getDescription());
        job.setRequirements(gson.toJson(jobAddBO.getRequirements()));
        job.setDate(jobAddBO.getDate());
        job.setCategory(jobAddBO.getCategory());
        job.setCount(jobAddBO.getCount());
        return updateById(job);
    }
}