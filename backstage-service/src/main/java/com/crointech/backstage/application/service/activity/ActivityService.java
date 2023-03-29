package com.crointech.backstage.application.service.activity;

import com.crointech.backstage.common.req.activity.ActivityDetailReq;
import com.crointech.backstage.common.req.activity.ActivityReq;
import com.crointech.backstage.common.resp.activity.ActivityDetailResp;
import com.crointech.backstage.common.resp.activity.ActivityResp;
import com.crointech.croincommon.common.DomainPageableEntity;

/**
 * @author Jason Q
 * @create 2023-01-13 15:05
 * @Description 活动相关
 **/
public interface ActivityService {

    /**
     * 活动列表
     *
     * @param req 活动列表请求
     * @return 活动列表
     */
    DomainPageableEntity<ActivityResp> queryActivityList(ActivityReq req);

    /**
     * 查看活动详情
     *
     * @param req 活动详情请求
     * @return 活动详情
     */
    ActivityDetailResp findDetail(ActivityDetailReq req);
}
