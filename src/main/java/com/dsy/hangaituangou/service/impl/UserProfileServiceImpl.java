package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.UserProfile;
import com.dsy.hangaituangou.domain.bo.UserProfileAppBO;
import com.dsy.hangaituangou.domain.bo.UserProfileBO;
import com.dsy.hangaituangou.domain.vo.UserProfileAppVO;
import com.dsy.hangaituangou.domain.vo.UserProfileVO;
import com.dsy.hangaituangou.mapper.UserProfileMapper;
import com.dsy.hangaituangou.service.UserProfileService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 用户档案Service业务层处理
 */
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements UserProfileService {

    private final UserProfileMapper userProfileMapper;

    private final Gson gson = new Gson();

    /**
     * 查询用户档案
     *
     * @param id ID
     * @return 用户档案
     */
    @Override
    public UserProfileAppVO selectUserProfileById(String id) {

        UserProfile userProfile = list(new LambdaQueryWrapper<UserProfile>().eq(UserProfile::getUserId, id)).stream().findFirst().orElse(new UserProfile());

        if (Objects.nonNull(userProfile.getUserId())) {
            List<String> specialty = gson.fromJson(userProfile.getSpecialty(), new TypeToken<List<String>>() {
            }.getType());

            return UserProfileAppVO.builder()
                    .id(String.valueOf(userProfile.getId()))
                    .userId(userProfile.getUserId())
                    .exJob(userProfile.getExJob())
                    .exMinSalary(userProfile.getExMinSalary())
                    .exMaxSalary(userProfile.getExMaxSalary())
                    .personIntroduction(userProfile.getPersonIntroduction())
                    .workExperience(userProfile.getWorkExperience())
                    .name(userProfile.getName())
                    .specialty(specialty)
                    .city(userProfile.getCity())
                    .build();
        } else {
            return null;
        }

    }

    /**
     * 根据用户ID查询用户档案
     *
     * @param userId 用户ID
     * @return 用户档案
     */
    @Override
    public UserProfile selectUserProfileByUserId(String userId) {
        return userProfileMapper.selectUserProfileByUserId(userId);
    }

    /**
     * 查询用户档案列表
     *
     * @param userProfile 用户档案
     * @return 用户档案
     */
    @Override
    public Page<UserProfileVO> selectUserProfileList(UserProfileBO userProfile) {
        Page<UserProfile> profilePage = page(new Page<>(userProfile.getPageNum(), userProfile.getPageSize()),
                new LambdaQueryWrapper<UserProfile>()
                        .and(Objects.nonNull(userProfile.getSearchKey()),
                                wrapper -> wrapper
                                        .like(UserProfile::getExJob, userProfile.getSearchKey())
                                        .or()
                                        .like(UserProfile::getExJob, userProfile.getSearchKey()))
                        .like(Objects.nonNull(userProfile.getCommon()), UserProfile::getSpecialty, userProfile.getCommon()));

        Page<UserProfileVO> userProfileVOPage = new Page<>();

        List<UserProfileVO> userProfileVOS = profilePage.getRecords().stream().map(userProfile1 -> {
            List<String> skillList = gson.fromJson(userProfile1.getSpecialty(), new TypeToken<List<String>>() {
            }.getType());
            return UserProfileVO.builder().id(userProfile1.getUserId()).name(userProfile1.getName()).title(userProfile1.getExJob()).skills(skillList).location(userProfile1.getCity()).experience(userProfile1.getWorkExperience()).avatar("").introduction(userProfile1.getPersonIntroduction()).expectedSalary(userProfile1.getExSalary()).isHovered("true").isBlurred("true").category(userProfile1.getSpecialty()).build();
        }).toList();

        userProfileVOPage.setRecords(userProfileVOS);
        userProfileVOPage.setTotal(profilePage.getTotal());
        userProfileVOPage.setSize(profilePage.getSize());
        userProfileVOPage.setCurrent(profilePage.getCurrent());
        return userProfileVOPage;
    }

    /**
     * 新增用户档案
     *
     * @param userProfileAppBO 用户档案
     * @return 结果
     */
    @Override
    public boolean insertUserProfile(UserProfileAppBO userProfileAppBO) {
        UserProfile userProfile = UserProfile.builder()
                .userId(Long.valueOf(userProfileAppBO.getUserId()))
                .name(userProfileAppBO.getName())
                .exJob(userProfileAppBO.getExJob())
                .exMinSalary(userProfileAppBO.getExMinSalary())
                .exMaxSalary(userProfileAppBO.getExMaxSalary())
                .exSalary(userProfileAppBO.getExMinSalary() + "-" + userProfileAppBO.getExMaxSalary())
                .personIntroduction(userProfileAppBO.getPersonIntroduction())
                .workExperience(userProfileAppBO.getWorkExperience())
                .specialty(gson.toJson(userProfileAppBO.getSpecialty()))
                .city(userProfileAppBO.getCity())
                .build();

        if (Objects.nonNull(userProfileAppBO.getId())){
            userProfile.setId(Long.valueOf(userProfileAppBO.getId()));
        }

        return userProfileMapper.insertOrUpdate(userProfile);
    }


    /**
     * 删除用户档案对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserProfileByIds(String[] ids) {
        return userProfileMapper.deleteUserProfileByIds(ids);
    }

    /**
     * 删除用户档案信息
     *
     * @param id ID
     * @return 结果
     */
    @Override
    public int deleteUserProfileById(String id) {
        return userProfileMapper.deleteUserProfileById(id);
    }
}