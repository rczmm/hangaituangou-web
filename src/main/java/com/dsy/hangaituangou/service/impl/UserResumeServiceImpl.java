package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.UserResume;
import com.dsy.hangaituangou.domain.bo.UserResumeBO;
import com.dsy.hangaituangou.domain.vo.UserResumeVO;
import com.dsy.hangaituangou.mapper.UserResumeMapper;
import com.dsy.hangaituangou.service.UserResumeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserResumeServiceImpl extends ServiceImpl<UserResumeMapper, UserResume> implements UserResumeService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Gson gson = new Gson();

    @Override
    @Transactional
    public UserResume updateResume(Long id, UserResumeBO resumeBO) {
        UserResume resume = new UserResume();
        if (id != null) {
            resume = getById(id);
        }
        BeanUtils.copyProperties(resumeBO, resume);
        try {
            resume.setWorkExperience(objectMapper.writeValueAsString(resumeBO.getWorkExperiences()));
            resume.setProjectExperience(objectMapper.writeValueAsString(resumeBO.getProjectExperiences()));
            resume.setEducationHistory(objectMapper.writeValueAsString(resumeBO.getEducationExperiences()));
            resume.setHonorsAwards(objectMapper.writeValueAsString(resumeBO.getHonors()));
            resume.setCertificates(objectMapper.writeValueAsString(resumeBO.getCertifications()));
            resume.setProfessionalSkills(objectMapper.writeValueAsString(resumeBO.getSkills()));
            resume.setPersonalStrengths(objectMapper.writeValueAsString(resumeBO.getStrengths()));
            resume.setJobExpectations(objectMapper.writeValueAsString(resumeBO.getExpectations()));
            resume.setJobSeekingStatus(objectMapper.writeValueAsString(resumeBO.getJobStatus()));
            resume.setPersonalityTraits(objectMapper.writeValueAsString(resumeBO.getPersonality()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON fields", e);
        }
        saveOrUpdate(resume);
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
    public List<UserResumeVO> getResumesByUserId(Long userId) {
        QueryWrapper<UserResume> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<UserResume> resumes = list(wrapper);
        return resumes.stream()
                .map(resume -> {
                    List<Map<String, Object>> workExperience = gson.fromJson(resume.getWorkExperience(), new TypeToken<List<Map<String, Object>>>() {
                    }.getType());
                    List<Map<String, Object>> projectExperience = gson.fromJson(resume.getProjectExperience(), new TypeToken<List<Map<String, Object>>>() {
                    }.getType());
                    List<Map<String, Object>> educationHistory = gson.fromJson(resume.getEducationHistory(), new TypeToken<List<Map<String, Object>>>() {
                    }.getType());
                    List<String> honorsAwards = gson.fromJson(resume.getHonorsAwards(), new TypeToken<List<String>>() {
                    }.getType());
                    List<String> certificates = gson.fromJson(resume.getCertificates(), new TypeToken<List<String>>() {
                    }.getType());
                    List<String> skills = gson.fromJson(resume.getProfessionalSkills(), new TypeToken<List<String>>() {
                    }.getType());
                    UserResumeVO profileVO = new UserResumeVO();
                    profileVO.setId(resume.getId().toString());
                    profileVO.setName(resume.getName());
                    profileVO.setUserId(resume.getUserId().toString());
                    profileVO.setPhone(resume.getPhone());
                    profileVO.setEmail(resume.getEmail());
                    profileVO.setAddress(resume.getAddress());
                    profileVO.setJobStatus(resume.getJobSeekingStatus());
                    profileVO.setWorkExperiences(workExperience);
                    profileVO.setProjectExperiences(projectExperience);
                    profileVO.setEducationExperiences(educationHistory);
                    profileVO.setHonors(honorsAwards);
                    profileVO.setCertifications(certificates);
                    profileVO.setSkills(skills);
                    profileVO.setPersonality(resume.getPersonalityTraits());
                    profileVO.setStrengths(resume.getPersonalStrengths());
                    profileVO.setExpectations(resume.getJobExpectations());
                    return profileVO;
                })
                .collect(Collectors.toList());
    }
}