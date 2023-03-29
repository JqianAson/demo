package com.crointech.backstage.application.service.impl;

import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.crointech.backstage.application.handler.MarketingHandler;
import com.crointech.backstage.application.handler.MarketingHandlerFactory;
import com.crointech.backstage.application.service.activity.ActivityRulesInitService;
import com.crointech.backstage.common.activityenum.ActivityBusinessMsg;
import com.crointech.backstage.common.dto.ActivityDetailDto;
import com.crointech.backstage.domain.entity.activity.Activity;
import com.crointech.backstage.domain.entity.activity.ActivityTemplate;
import com.crointech.backstage.domain.mapper.ActivityMapper;
import com.crointech.backstage.domain.mapper.ActivityTemplateMapper;
import com.crointech.croincommon.common.BusinessException;
import com.crointech.croincommon.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason Q
 * @create 2023-01-13 17:17
 * @Description
 **/
@Slf4j
@Service
public class ActivityRulesInitServiceImpl implements ActivityRulesInitService {


    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityTemplateMapper activityTemplateMapper;

    /**
     * 转换活动规则为通用实体
     *
     * @param activityId 活动id
     * @return 通用实体
     */
    @Override
    public ActivityDetailDto convertActivityRules(String activityId) {
        log.info("ActivityRulesInitServiceImpl #convertActivityRules #param:{}", activityId);
        Activity activity = activityMapper.findActivity(activityId);
        log.info("ActivityRulesInitServiceImpl #convertActivityRules #activity:{}", activity);
        if (null == activity) {
            throw new BusinessException(ActivityBusinessMsg.MAAE0020);
        }
        log.info("ActivityRulesInitServiceImpl #setActivity #activity:{}", JSON.toJSONString(activity));
        return this.setActivity(activity);
    }

    /**
     * 设置活动规则
     *
     * @param activity 活动信息
     */
    private ActivityDetailDto setActivity(Activity activity) {

        log.info("ActivityRulesInitServiceImpl #setActivity #start time:{}", DateUtils.dateToString(DateUtils.getCurDate()));
        ActivityDetailDto detailDto = new ActivityDetailDto();
        detailDto.setActivity(activity);

        ActivityTemplate activityTemp = activityTemplateMapper.getTemplateById(activity.getActivityTemplateId());
        log.info("ActivityRulesInitServiceImpl #setActivity #activityTemp:{}", JSON.toJSONString(activityTemp));
        detailDto.setActivityTemplate(activityTemp);

        log.info("ActivityRulesInitServiceImpl #setActivity #subType:{}", activityTemp.getSubType().getDesc());
        MarketingHandler handler = MarketingHandlerFactory.getMarketingHandler(activityTemp.getSubType());
        return handler.convertActivityRules(detailDto);
    }
}
