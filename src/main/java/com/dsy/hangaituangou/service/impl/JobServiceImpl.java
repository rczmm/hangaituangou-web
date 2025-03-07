package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.Job;
import com.dsy.hangaituangou.domain.Notification;
import com.dsy.hangaituangou.mapper.JobMapper;
import com.dsy.hangaituangou.service.JobService;
import com.dsy.hangaituangou.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位服务实现类
 */
@Service
@RequiredArgsConstructor
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {
    
    private final NotificationService notificationService;
    
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
    
    /**
     * 获取岗位相关通知
     * @param jobId 岗位ID
     * @return 通知列表
     */
    @Override
    public List<Notification> getJobNotifications(Long jobId) {
        // 根据岗位ID查询相关通知
        // 这里假设Notification表中有一个jobId字段关联岗位
        return notificationService.list(new LambdaQueryWrapper<Notification>()
                .eq(Notification::getReceiverId, jobId)
                .orderByDesc(Notification::getCreateTime));
    }
    
    /**
     * 为岗位创建通知
     * @param jobId 岗位ID
     * @param notification 通知信息
     * @return 是否成功
     */
    @Override
    public boolean createJobNotification(Long jobId, Notification notification) {
        // 设置通知的接收者ID为岗位ID
        notification.setReceiverId(jobId);
        return notificationService.createNotification(notification);
    }
    
    /**
     * 获取岗位未读通知数量
     * @param jobId 岗位ID
     * @return 未读通知数量
     */
    @Override
    public int getUnreadJobNotificationCount(Long jobId) {
        // 查询岗位相关的未读通知数量
        return (int) notificationService.count(new LambdaQueryWrapper<Notification>()
                .eq(Notification::getReceiverId, jobId)
                .eq(Notification::getStatus, 0));
    }
}