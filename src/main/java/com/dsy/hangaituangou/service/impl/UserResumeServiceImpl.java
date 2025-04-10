package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.UserResume;
import com.dsy.hangaituangou.domain.bo.UserResumeBO;
import com.dsy.hangaituangou.mapper.UserResumeMapper;
import com.dsy.hangaituangou.service.UserResumeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserResumeServiceImpl extends ServiceImpl<UserResumeMapper, UserResume> implements UserResumeService {
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional
    public UserResume createResume(UserResumeBO resumeBO) {
        UserResume resume = new UserResume();
        BeanUtils.copyProperties(resumeBO, resume);
        
        try {
            resume.setWorkExperience(objectMapper.writeValueAsString(resumeBO.getWorkExperience()));
            resume.setProjectExperience(objectMapper.writeValueAsString(resumeBO.getProjectExperience()));
            resume.setEducationHistory(objectMapper.writeValueAsString(resumeBO.getEducationHistory()));
            resume.setHonorsAwards(objectMapper.writeValueAsString(resumeBO.getHonorsAwards()));
            resume.setCertificates(objectMapper.writeValueAsString(resumeBO.getCertificates()));
            resume.setProfessionalSkills(objectMapper.writeValueAsString(resumeBO.getProfessionalSkills()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON fields", e);
        }
        
        save(resume);
        return resume;
    }

    @Override
    @Transactional
    public UserResume updateResume(Long id, UserResumeBO resumeBO) {
        UserResume resume = getById(id);
        if (resume == null) {
            throw new RuntimeException("Resume not found");
        }
        
        BeanUtils.copyProperties(resumeBO, resume);
        
        try {
            resume.setWorkExperience(objectMapper.writeValueAsString(resumeBO.getWorkExperience()));
            resume.setProjectExperience(objectMapper.writeValueAsString(resumeBO.getProjectExperience()));
            resume.setEducationHistory(objectMapper.writeValueAsString(resumeBO.getEducationHistory()));
            resume.setHonorsAwards(objectMapper.writeValueAsString(resumeBO.getHonorsAwards()));
            resume.setCertificates(objectMapper.writeValueAsString(resumeBO.getCertificates()));
            resume.setProfessionalSkills(objectMapper.writeValueAsString(resumeBO.getProfessionalSkills()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON fields", e);
        }
        
        updateById(resume);
        return resume;
    }

    @Override
    @Transactional
    public void deleteResume(Long id) {
        removeById(id);
    }

    @Override
    public UserResume getResumeById(Long id) {
        return getById(id);
    }

    @Override
    public List<UserResume> getResumesByUserId(Long userId) {
        QueryWrapper<UserResume> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return list(wrapper);
    }
}