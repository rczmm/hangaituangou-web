package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.UserProfile;
import com.dsy.hangaituangou.domain.bo.UserProfileBO;
import com.dsy.hangaituangou.domain.vo.UserProfileVO;

import java.util.List;

/**
 * 用户档案Service接口
 */
public interface UserProfileService extends IService<UserProfile> {
    
    /**
     * 查询用户档案
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
    Page<UserProfileVO> selectUserProfileList(UserProfileBO userProfile);

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
     * 批量删除用户档案
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteUserProfileByIds(String[] ids);

    /**
     * 删除用户档案信息
     *
     * @param id ID
     * @return 结果
     */
    int deleteUserProfileById(String id);
}