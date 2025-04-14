package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.Interview;
import com.dsy.hangaituangou.domain.bo.InterviewBO;
import com.dsy.hangaituangou.domain.vo.InterviewVO;
import com.dsy.hangaituangou.enums.InterviewStatusEnum;
import com.dsy.hangaituangou.mapper.InterviewMapper;
import com.dsy.hangaituangou.service.InterviewService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class InterviewServiceImpl extends ServiceImpl<InterviewMapper, Interview> implements InterviewService {
    @Override
    public Boolean acceptInterview(String id) {

        Interview interview = getById(id);

        if (interview != null && interview.getStatus().equals(InterviewStatusEnum.PENDING_RECEIPT)) {
            interview.setStatus(InterviewStatusEnum.PENDING);
            return updateById(interview);
        } else if (interview != null && interview.getStatus().equals(InterviewStatusEnum.PENDING)) {
            throw new RuntimeException("你已经接受了面试");
        }
        return false;
    }

    @Override
    public Page<InterviewVO> listInterview(InterviewBO interview) {

        Page<Interview> page = page(new Page<>(interview.getPageNum(), interview.getPageSize()),
                new LambdaQueryWrapper<Interview>().eq(Objects.nonNull(interview.getJobName()), Interview::getJobName, interview.getJobName()));

        if (page != null) {
            Page<InterviewVO> pageVO = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
            pageVO.setRecords(page.getRecords().stream().map(item -> {
                InterviewVO interviewVO = new InterviewVO();
                interviewVO.setId(String.valueOf(item.getId()));
                interviewVO.setJobName(item.getJobName());
                interviewVO.setType(item.getType());
                interviewVO.setScheduleTime(item.getScheduledTime());
                interviewVO.setDurationTime(item.getDurationTime());
                interviewVO.setLocation(item.getLocation());
                interviewVO.setStatus(item.getStatus().getDescription());
                return interviewVO;
            }).toList());
            pageVO.setSize(page.getSize());
            pageVO.setCurrent(page.getCurrent());
            pageVO.setTotal(page.getTotal());
            return pageVO;
        }

        return null;
    }
}
