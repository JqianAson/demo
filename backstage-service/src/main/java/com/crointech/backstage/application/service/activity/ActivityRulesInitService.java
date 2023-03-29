package com.crointech.backstage.application.service.activity;

import com.crointech.backstage.common.dto.ActivityDetailDto;

/**
 * @author Jason Q
 * @create 2023-01-13 17:16
 * @Description 活动规则公共service
 **/
public interface ActivityRulesInitService {


    /**
     * 转换活动规则为通用实体
     *
     * @param activityId 活动id
     * @return 通用实体
     */
    ActivityDetailDto convertActivityRules(String activityId);
}
