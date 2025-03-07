package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.Candidate;

import java.util.List;

/**
 * 候选人服务接口
 */
public interface CandidateService extends IService<Candidate> {
    // 移除了根据租户ID查询候选人列表的方法
}