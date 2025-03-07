package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.Candidate;
import com.dsy.hangaituangou.mapper.CandidateMapper;
import com.dsy.hangaituangou.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 候选人服务实现类
 */
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl extends ServiceImpl<CandidateMapper, Candidate> implements CandidateService {

    /**
     * 根据租户ID查询候选人列表
     * @param tenantId 租户ID
     * @return 候选人列表
     */
    @Override
    public List<Candidate> getCandidatesByTenantId(Long tenantId) {
        return list(new LambdaQueryWrapper<Candidate>()
                .eq(Candidate::getTenantId, tenantId));
    }

    /**
     * 添加新候选人
     * @param candidate 候选人信息
     * @return 是否成功
     */
    @Override
    public boolean addCandidate(Candidate candidate) {
        return save(candidate);
    }

    /**
     * 更新候选人信息
     * @param candidate 候选人信息
     * @return 是否成功
     */
    @Override
    public boolean updateCandidate(Candidate candidate) {
        return updateById(candidate);
    }

    /**
     * 更新候选人面试状态
     * @param candidateId 候选人ID
     * @param interviewStatus 面试状态
     * @return 是否成功
     */
    @Override
    public boolean updateInterviewStatus(String candidateId, String interviewStatus) {
        Candidate candidate = getOne(new LambdaQueryWrapper<Candidate>()
                .eq(Candidate::getCandidateId, candidateId));
        if (candidate != null) {
            candidate.setInterviewStatus(interviewStatus);
            return updateById(candidate);
        }
        return false;
    }

    /**
     * 更新候选人招聘状态
     * @param candidateId 候选人ID
     * @param hiringStatus 招聘状态
     * @return 是否成功
     */
    @Override
    public boolean updateHiringStatus(String candidateId, String hiringStatus) {
        Candidate candidate = getOne(new LambdaQueryWrapper<Candidate>()
                .eq(Candidate::getCandidateId, candidateId));
        if (candidate != null) {
            candidate.setHiringStatus(hiringStatus);
            return updateById(candidate);
        }
        return false;
    }

    /**
     * 添加应聘岗位记录
     * @param candidateId 候选人ID
     * @param appliedJob 应聘岗位信息
     * @return 是否成功
     */
    @Override
    public boolean addAppliedJob(String candidateId, Candidate.AppliedJob appliedJob) {
        Candidate candidate = getOne(new LambdaQueryWrapper<Candidate>()
                .eq(Candidate::getCandidateId, candidateId));
        if (candidate != null) {
            List<Candidate.AppliedJob> appliedJobs = candidate.getAppliedJobs();
            if (appliedJobs == null) {
                appliedJobs = new ArrayList<>();
            }
            appliedJobs.add(appliedJob);
            candidate.setAppliedJobs(appliedJobs);
            return updateById(candidate);
        }
        return false;
    }

    /**
     * 更新面试安排
     * @param candidateId 候选人ID
     * @param interviewSchedule 面试安排信息
     * @return 是否成功
     */
    @Override
    public boolean updateInterviewSchedule(String candidateId, Candidate.InterviewSchedule interviewSchedule) {
        Candidate candidate = getOne(new LambdaQueryWrapper<Candidate>()
                .eq(Candidate::getCandidateId, candidateId));
        if (candidate != null) {
            candidate.setInterviewSchedule(interviewSchedule);
            // 更新面试状态为已安排
            candidate.setInterviewStatus("已安排面试");
            return updateById(candidate);
        }
        return false;
    }

    /**
     * 更新面试反馈
     * @param candidateId 候选人ID
     * @param feedback 面试反馈
     * @return 是否成功
     */
    @Override
    public boolean updateInterviewFeedback(String candidateId, String feedback) {
        Candidate candidate = getOne(new LambdaQueryWrapper<Candidate>()
                .eq(Candidate::getCandidateId, candidateId));
        if (candidate != null) {
            candidate.setInterviewFeedback(feedback);
            // 更新面试状态为已完成
            candidate.setInterviewStatus("面试已完成");
            return updateById(candidate);
        }
        return false;
    }

    /**
     * 更新简历文件信息
     * @param candidateId 候选人ID
     * @param resumeFile 简历文件信息
     * @return 是否成功
     */
    @Override
    public boolean updateResumeFile(String candidateId, Candidate.ResumeFile resumeFile) {
        Candidate candidate = getOne(new LambdaQueryWrapper<Candidate>()
                .eq(Candidate::getCandidateId, candidateId));
        if (candidate != null) {
            candidate.setResumeFile(resumeFile);
            return updateById(candidate);
        }
        return false;
    }
}