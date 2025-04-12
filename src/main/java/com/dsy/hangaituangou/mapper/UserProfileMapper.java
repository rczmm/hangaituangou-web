package com.dsy.hangaituangou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dsy.hangaituangou.domain.UserProfile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfile> {
    
    /**
     * 根据ID查询用户档案
     *
     * @param id ID
     * @return 用户档案
     */
    UserProfile selectUserProfileById(String id);

    /**
     * 根据用户ID查询用户档案
     *
     * @param userId 用户ID
     * @return 用户档案
     */
    UserProfile selectUserProfileByUserId(String userId);

    /**
     * 查询用户档案列表
     *
     * @param userProfile 用户档案
     * @return 用户档案集合
     */
    List<UserProfile> selectUserProfileList(UserProfile userProfile);

    /**
     * 新增用户档案
     *
     * @param userProfile 用户档案
     * @return 结果
     */
    int insertUserProfile(UserProfile userProfile);

    /**
     * 修改用户档案
     *
     * @param userProfile 用户档案
     * @return 结果
     */
    int updateUserProfile(UserProfile userProfile);

    /**
     * 删除用户档案
     *
     * @param id ID
     * @return 结果
     */
    int deleteUserProfileById(String id);

    /**
     * 批量删除用户档案
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteUserProfileByIds(String[] ids);
}