package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.Job;
import com.dsy.hangaituangou.domain.Notification;

import java.util.List;

/**
 * 岗位服务接口
 */
public interface JobService extends IService<Job> {
    /**
     * 根据租户ID查询岗位列表
     * @param tenantId 租户ID
     * @return 岗位列表
     */
    List<Job> getJobsByTenantId(Long tenantId);
    
    /**
     * 发布新岗位
     * @param job 岗位信息
     * @return 是否成功
     */
    boolean publishJob(Job job);
    
    /**
     * 更新岗位状态
     * @param jobId 岗位ID
     * @param status 新状态
     * @return 是否成功
     */
    boolean updateJobStatus(Long jobId, String status);
    
    /**
     * 获取岗位相关通知
     * @param jobId 岗位ID
     * @return 通知列表
     */
    List<Notification> getJobNotifications(Long jobId);
    
    /**
     * 为岗位创建通知
     * @param jobId 岗位ID
     * @param notification 通知信息
     * @return 是否成功
     */
    boolean createJobNotification(Long jobId, Notification notification);
    
    /**
     * 获取岗位未读通知数量
     * @param jobId 岗位ID
     * @return 未读通知数量
     */
    int getUnreadJobNotificationCount(Long jobId);
}