package com.crointech.backstage.application.service.activity;

import com.crointech.backstage.domain.entity.activityaccount.ActivityUser;

import java.util.List;

/**
 * @author Jason Q
 * @create 2023-01-13 15:10
 * @Description 系统用户相关
 **/
public interface SystemUserManagementService {

    /**
     * 根据用户Id和商户号获取用户信息
     *
     * @param userId       用户Id
     * @return 用户信息
     */
    ActivityUser queryUserInfoById(Integer userId);

    /**
     * 根据用户ID集合查询用户信息
     *
     * @param userIds 用户ID集合
     * @return 用户信息
     */
    List<ActivityUser> loadUserInfoListByUserIdList(List<Integer> userIds);
}
