package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.Job;
import com.dsy.hangaituangou.domain.bo.JobBO;
import com.dsy.hangaituangou.domain.vo.JobVO;

/**
 * 岗位服务接口
 */
public interface JobService extends IService<Job> {
    Page<JobVO> listByParams(JobBO jobBO);

    Object getTags();

}