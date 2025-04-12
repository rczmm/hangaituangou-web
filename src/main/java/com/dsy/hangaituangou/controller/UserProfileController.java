package com.dsy.hangaituangou.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsy.hangaituangou.domain.UserProfile;
import com.dsy.hangaituangou.domain.bo.UserProfileBO;
import com.dsy.hangaituangou.domain.vo.UserProfileVO;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("userProfile")
@RequiredArgsConstructor
@Tag(name = "用户档案管理", description = "用户档案相关接口")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Operation(summary = "获取用户档案详细信息", description = "根据ID获取用户档案详细信息")
    @GetMapping(value = "/{id}")
    public RespBase<UserProfile> getInfo(@PathVariable("id") String id) {
        return RespBase.success(userProfileService.selectUserProfileById(id));
    }

    @Operation(summary = "获取用户档案列表", description = "获取用户档案列表")
    @PostMapping("/list")
    public RespBase<Page<UserProfileVO>> list(@RequestBody UserProfileBO userProfile) {
        Page<UserProfileVO> list = userProfileService.selectUserProfileList(userProfile);
        return RespBase.success(list);
    }

    @Operation(summary = "新增用户档案", description = "新增用户档案")
    @PostMapping
    public RespBase<Integer> add(@RequestBody UserProfile userProfile) {
        return RespBase.success(userProfileService.insertUserProfile(userProfile));
    }

    @Operation(summary = "修改用户档案", description = "修改用户档案")
    @PutMapping
    public RespBase<Integer> edit(@RequestBody UserProfile userProfile) {
        return RespBase.success(userProfileService.updateUserProfile(userProfile));
    }

    @Operation(summary = "删除用户档案", description = "删除用户档案")
    @DeleteMapping("/{ids}")
    public RespBase<Integer> remove(@PathVariable String[] ids) {
        return RespBase.success(userProfileService.deleteUserProfileByIds(ids));
    }

    @Operation(summary = "获取用户档案信息", description = "根据用户ID获取用户档案信息")
    @GetMapping("/user/{userId}")
    public RespBase<UserProfile> getProfileByUserId(@PathVariable("userId") String userId) {
        return RespBase.success(userProfileService.selectUserProfileByUserId(userId));
    }
}