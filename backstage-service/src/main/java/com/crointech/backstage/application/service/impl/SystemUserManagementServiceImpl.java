package com.crointech.backstage.application.service.impl;

import com.crointech.backstage.application.service.activity.SystemUserManagementService;
import com.crointech.backstage.domain.entity.activityaccount.ActivityUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Q
 * @create 2023-01-13 15:11
 * @Description 系统用户相关实现
 **/
@Service
public class SystemUserManagementServiceImpl implements SystemUserManagementService {

    /**
     * 根据用户Id和商户号获取用户信息
     *
     * @param userId       用户Id
     * @param merchantCode 商户号
     * @return 用户信息
     */
    @Override
    public ActivityUser queryUserInfoById(Integer userId) {
        return null;
    }

    /**
     * 根据用户ID集合查询用户信息
     *
     * @param userIds 用户ID集合
     * @return 用户信息
     */
    @Override
    public List<ActivityUser> loadUserInfoListByUserIdList(List<Integer> userIds) {
        return null;
    }
}
