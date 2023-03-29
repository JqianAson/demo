package com.crointech.backstage.domain.mapper;

import com.crointech.backstage.domain.entity.activity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author hp
 * @create 2021-11-15
 * @Description
 **/
@Mapper
public interface ActivityMapper {


    List<Activity> queryActivityList(@Param("merchantsId") String merchantsId,
                                     @Param("visible") Boolean visible,
                                     @Param("status") Integer status,
                                     @Param("title") String title);

    Activity findActivity(@Param("activityId") String activityId);

    void insertActivity(Activity activity);

    void updateActivityAll(Activity activity);

    void updateActivityOngoing(Activity activity);

    List<Activity> findActivityByTitle(String title, Integer merchantsId);

    void batchEditVisible(@Param("activityList") List<Activity> activityList);

    List<Activity> getActivityByIds(@Param("activityIds") List<String> activityIds);

    List<Activity> getMatActivityByIds(@Param("activityIds") List<String> activityIds, @Param("visible") Integer visible, @Param("statusList") List<Integer> statusList);

    List<Integer> loadAllActivityAccountNotEndAndWaiting(@Param("merchantsId") Integer merchantsId);

    void updateActivityStatus(Activity activity);

    /**
     * 获取所有待开始的活动
     *
     * @param date 当前时间
     * @return 活动列表
     */
    List<Activity> loadNotStartActivity(Date date);

    /**
     * 修改活动状态
     *
     * @param activity 活动信息
     */
    void editActivityStatus(Activity activity);

    /**
     * 获取所有已结束的活动
     *
     * @param currentDate 当前时间
     * @return 结束活动列表
     */
    List<Activity> loadHasEndedActivity(Date currentDate);

    /**
     * 获取所有正在进行中的活动
     *
     * @return 活动ID列表
     */
    List<String> loadAllOngoingActivity();

    /**
     * 获取所有状态不是已保存和待开始的活动
     *
     * @return 活动结合
     */
    List<Activity> loadAllActivity();

    /**
     * 根据商户号和活动ID获取活动信息
     *
     * @param merchantsId 商户号
     * @param activityId  活动ID
     * @return 活动信息
     */
    Activity loadActivityByActivityId(@Param("merchantsId") Integer merchantsId,
                                      @Param("activityId") String activityId);

    List<Activity> startedList(@Param("merchantsId") Integer merchantsId);

    List<Activity> notEndList(@Param("merchantsId") Integer merchantsId);

    /**
     * 修改规则内容
     *
     * @param activity 活动实体
     */
    void editActivityRule(Activity activity);

    /**
     * 通过模板id查询创建时间最新的一条进行中活动
     *
     * @param templateIds
     * @param activityIds
     * @param merchantsId
     * @return
     */
    List<Activity> getNewActivityByTemplateIds(@Param("templateIds") List<String> templateIds, @Param("activityIds") List<String> activityIds, @Param("merchantsId") Integer merchantsId);

    /**
     * 根据活动标题模糊查询活动实体
     * @param activityTitle
     * @return
     */
    List<Activity> findByActivityTitle(@Param("activityTitle") String activityTitle,@Param("merchantsId") Integer merchantsId);

    /**
     * 获取全部活动
     * @param merchantsId
     * @return
     */
    List<Activity> findAllActivity(@Param("merchantsId")Integer merchantsId);

    /**
     * 根据活动流水號查询活动实体
     * @param id
     * @return
     */
	Activity findById(@Param("id")Integer id);

    /**
     * 获取补充规则活动列表
     *
     * @return 活动列表
     */
    List<Activity> loadSupplementaryRuleList();

    /**
     * 去除活动规则为null的数据
     *
     * @param activityList 活动序号
     * @return 筛选后的活动信息
     */
    List<Activity> screeningActivity(@Param("activityList") List<Integer> activityList);

    List<Activity> loadActivityByActivityIdList(@Param("merchantsId") Integer merchantsId,
                                                @Param("activityIdList") List<String> activityIdList);
}
