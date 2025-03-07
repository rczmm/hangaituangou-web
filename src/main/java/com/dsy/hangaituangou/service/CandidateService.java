package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.Candidate;

import java.util.List;

/**
 * 候选人服务接口
 */
public interface CandidateService extends IService<Candidate> {
    /**
     * 根据租户ID查询候选人列表
     * @param tenantId 租户ID
     * @return 候选人列表
     */
    List<Candidate> getCandidatesByTenantId(Long tenantId);
    
    /**
     * 添加新候选人
     * @param candidate 候选人信息
     * @return 是否成功
     */
    boolean addCandidate(Candidate candidate);
    
    /**
     * 更新候选人信息
     * @param candidate 候选人信息
     * @return 是否成功
     */
    boolean updateCandidate(Candidate candidate);
    
    /**
     * 更新候选人面试状态
     * @param candidateId 候选人ID
     * @param interviewStatus 面试状态
     * @return 是否成功
     */
    boolean updateInterviewStatus(String candidateId, String interviewStatus);
    
    /**
     * 更新候选人招聘状态
     * @param candidateId 候选人ID
     * @param hiringStatus 招聘状态
     * @return 是否成功
     */
    boolean updateHiringStatus(String candidateId, String hiringStatus);
    
    /**
     * 添加应聘岗位记录
     * @param candidateId 候选人ID
     * @param appliedJob 应聘岗位信息
     * @return 是否成功
     */
    boolean addAppliedJob(String candidateId, Candidate.AppliedJob appliedJob);
    
    /**
     * 更新面试安排
     * @param candidateId 候选人ID
     * @param interviewSchedule 面试安排信息
     * @return 是否成功
     */
    boolean updateInterviewSchedule(String candidateId, Candidate.InterviewSchedule interviewSchedule);
    
    /**
     * 更新面试反馈
     * @param candidateId 候选人ID
     * @param feedback 面试反馈
     * @return 是否成功
     */
    boolean updateInterviewFeedback(String candidateId, String feedback);
    
    /**
     * 更新简历文件信息
     * @param candidateId 候选人ID
     * @param resumeFile 简历文件信息
     * @return 是否成功
     */
    boolean updateResumeFile(String candidateId, Candidate.ResumeFile resumeFile);
}