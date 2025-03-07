package com.dsy.hangaituangou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dsy.hangaituangou.domain.Candidate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 候选人Mapper接口
 */
@Mapper
public interface CandidateMapper extends BaseMapper<Candidate> {
    // 基础的CRUD操作由MyBatis-Plus的BaseMapper提供
    // 可以在这里添加自定义的查询方法
}