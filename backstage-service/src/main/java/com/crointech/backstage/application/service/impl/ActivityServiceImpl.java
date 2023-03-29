package com.crointech.backstage.application.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crointech.backstage.application.service.activity.ActivityRulesInitService;
import com.crointech.backstage.application.service.activity.ActivityService;
import com.crointech.backstage.application.service.activity.SystemUserManagementService;
import com.crointech.backstage.common.activityenum.ActivityBusinessMsg;
import com.crointech.backstage.common.dto.ActivityDetailDto;
import com.crointech.backstage.common.req.activity.ActivityDetailReq;
import com.crointech.backstage.common.req.activity.ActivityReq;
import com.crointech.backstage.common.resp.activity.ActivityDetailResp;
import com.crointech.backstage.common.resp.activity.ActivityResp;
import com.crointech.backstage.domain.entity.activity.Activity;
import com.crointech.backstage.domain.entity.activity.ActivityTemplate;
import com.crointech.backstage.domain.entity.activityaccount.ActivityUser;
import com.crointech.backstage.domain.mapper.ActivityMapper;
import com.crointech.backstage.domain.mapper.ActivityTemplateMapper;
import com.crointech.croincommon.common.BusinessException;
import com.crointech.croincommon.common.DomainPageableEntity;
import com.crointech.croincommon.util.DateUtils;
import com.crointech.croincommon.util.StringUtil;
import com.crointech.croincommon.util.WebUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Jason Q
 * @create 2023-01-13 15:06
 * @Description 活动相关实现
 **/
@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {

    /**
     * title的长度限制
     */
    @Value("${limit.title}")
    private Integer titleLimit;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityTemplateMapper activityTemplateMapper;

    @Autowired
    private ActivityRulesInitService activityRulesInitService;

    @Autowired
    private SystemUserManagementService systemUserManagementService;

    /**
     * 活动列表
     *
     * @param req 活动列表请求
     * @return 活动列表
     */
    @Override
    public DomainPageableEntity<ActivityResp> queryActivityList(ActivityReq req) {
        if (req.getTitle() != null && req.getTitle().length() >= titleLimit) {
            throw new BusinessException(ActivityBusinessMsg.MAAE0002);
        }
        String title = StringUtil.isBlank(req.getTitle()) ? "" : req.getTitle().trim();
        PageHelper.startPage(req.getPage(), req.getLimit());
        List<Activity> list = activityMapper.queryActivityList(WebUtil.getMerchantCode(), req.getVisible(), req.getStatus(), title);
        if (!CollectionUtils.isEmpty(list)) {
            PageInfo<Activity> pageInfo = PageInfo.of(list);
            List<ActivityResp> result = this.convertList(list);
            return DomainPageableEntity.pageBuilder(pageInfo.getTotal(), result);
        }
        return DomainPageableEntity.pageBuilder(0L, new ArrayList<>());
    }

    /**
     * 查看活动详情
     *
     * @param req 活动详情请求
     * @return 活动详情
     */
    @Override
    public ActivityDetailResp findDetail(ActivityDetailReq req) {
        log.info("ActivityServiceImpl #findDetail #req:{}", JSON.toJSONString(req));
        //校验参数
        if (StringUtil.isBlank(req.getActivityId())) {
            throw new BusinessException(ActivityBusinessMsg.MAAE0021);
        }

        //设置活动及活动模板
        ActivityDetailDto activityDetailDto = activityRulesInitService.convertActivityRules(req.getActivityId());
        log.info("ActivityServiceImpl #findDetail #activityDetailDto:{}", JSON.toJSONString(activityDetailDto));

        //转换参数
        return this.convertDetail(activityDetailDto);
    }

    /**
     * 转换活动详情参数
     *
     * @param dto 活动详情通用实体
     * @return 活动详情
     */
    private ActivityDetailResp convertDetail(ActivityDetailDto dto) {
        Activity activity = dto.getActivity();
        ActivityTemplate template = dto.getActivityTemplate();
        JSONObject ruleContent = JSON.parseObject(activity.getRuleContent(), JSONObject.class);
        return new ActivityDetailResp(
            activity.getActivityId(),
            template.getTemplateType().getId(),
            template.getSubType().getId(),
            activity.getTitle(),
            activity.getStatus().getId(),
            activity.getStartTime() == null ? null : DateUtils.dateToString(activity.getStartTime()),
            activity.getEndTime() == null ? null : DateUtils.dateToString(activity.getEndTime()),
            activity.getVisible(),
            activity.getUrl(),
            activity.getBanner(),
            activity.getSmallBanner(),
            activity.getRemark(),
            Lists.newArrayList(),
            ruleContent
        );
    }

    /**
     * 查询结果转换Resp
     *
     * @param list 查询结果
     * @return Resp
     */
    private List<ActivityResp> convertList(List<Activity> list) {
        //批量查询模板
        List<String> templateIds = list.stream().map(Activity::getActivityTemplateId).collect(Collectors.toList());
        Map<String, ActivityTemplate> templateMap = null;
        if (!CollectionUtils.isEmpty(templateIds)) {
            List<ActivityTemplate> templateList = activityTemplateMapper.getTemplateByIds(templateIds);
            if (!CollectionUtils.isEmpty(templateList)) {
                templateMap = templateList.stream().collect(Collectors.toMap(ActivityTemplate::getTemplateId, r -> r));
            }
        }
        //批量查询用户
        List<Integer> userIds = list.stream().distinct().map(Activity::getOptUser).collect(Collectors.toList());
        Map<Integer, String> userMap = null;
        if (!CollectionUtils.isEmpty(userIds)) {
            List<ActivityUser> userList = systemUserManagementService.loadUserInfoListByUserIdList(userIds);
            if (!CollectionUtils.isEmpty(userList)) {
                userMap = userList.stream().collect(Collectors.toMap(ActivityUser::getUserId, ActivityUser::getUserName));
            }
        }
        List<ActivityResp> result = new ArrayList<>();
        for (Activity item : list) {
            result.add(new ActivityResp(
                item.getId(),
                item.getActivityId(),
                item.getTitle(),
                templateMap != null && templateMap.get(item.getActivityTemplateId()) != null ?
                    String.valueOf(templateMap.get(item.getActivityTemplateId()).getTemplateType().getId()) : null,
                item.getStatus().getId(),
                DateUtils.dateToString(item.getStartTime(), null),
                DateUtils.dateToString(item.getEndTime(), null),
                item.getVisible(),
                item.getOptUser() == null ? null : userMap == null ? null : userMap.get(item.getOptUser()),
                item.getOptTime() == null ? null : DateUtils.dateToString(item.getOptTime()),
                templateMap != null && templateMap.get(item.getActivityTemplateId()) != null ?
                    String.valueOf(templateMap.get(item.getActivityTemplateId()).getSubType().getId()) : null
            ));
        }
        return result;
    }
}
