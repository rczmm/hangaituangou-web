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



}