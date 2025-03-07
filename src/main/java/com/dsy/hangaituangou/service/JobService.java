package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.Job;
import com.dsy.hangaituangou.domain.Notification;

import java.util.List;

/**
 * 岗位服务接口
 */
public interface JobService extends IService<Job> {
    // 移除了根据租户ID查询岗位列表的方法
}