package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.Interview;
import com.dsy.hangaituangou.domain.bo.InterviewBO;
import com.dsy.hangaituangou.domain.vo.InterviewVO;

public interface InterviewService extends IService<Interview> {
    Boolean acceptInterview(String id);

    Page<InterviewVO> listInterview(InterviewBO interview);
}
