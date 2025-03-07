package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.Job;
import com.dsy.hangaituangou.mapper.JobMapper;
import com.dsy.hangaituangou.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位服务实现类
 */
@Service
@RequiredArgsConstructor
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {
    
    /**
     * 根据租户ID查询岗位列表
     * @param tenantId 租户ID
     * @return 岗位列表
     */
    public List<Job> getJobsByTenantId(Long tenantId) {
        return list(new LambdaQueryWrapper<Job>()
                .eq(Job::getTenantId, tenantId));
    }
    
    /**
     * 发布新岗位
     * @param job 岗位信息
     * @return 是否成功
     */
    public boolean publishJob(Job job) {
        return save(job);
    }
    
    /**
     * 更新岗位状态
     * @param jobId 岗位ID
     * @param status 新状态
     * @return 是否成功
     */
    public boolean updateJobStatus(Long jobId, String status) {
        Job job = getById(jobId);
        if (job != null) {
            job.setJobStatus(status);
            return updateById(job);
        }
        return false;
    }
}