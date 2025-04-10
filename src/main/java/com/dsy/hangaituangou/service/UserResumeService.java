package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.UserResume;
import com.dsy.hangaituangou.domain.bo.UserResumeBO;
import com.dsy.hangaituangou.domain.vo.ProfileVO;
import com.dsy.hangaituangou.domain.vo.UserResumeVO;

import java.util.List;

public interface UserResumeService extends IService<UserResume> {

    UserResume updateResume(Long id, UserResumeBO resumeBO);

    void deleteResume(Long id);

    UserResume getResumeById(Long id);

    List<UserResumeVO> getResumesByUserId(Long userId);
}