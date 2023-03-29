package com.crointech.backstage.domain.mapper;

import com.crointech.backstage.domain.entity.activity.ActivityTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hp
 * @create 2021-11-10
 * @Description
 **/
@Mapper
public interface ActivityTemplateMapper {

    /**
     * 获取活动模板列表
     * @param templateName 模板名称
     * @param status 模板状态
     * @return
     */
    List<ActivityTemplate> getTemplateList(@Param("templateName") String templateName, @Param("status") Boolean status);

    /**
     * 新增活动模板
     * @param activityTemplate 活动模板实体
     */
    void addTemplate(ActivityTemplate activityTemplate);

    /**
     * 修改活动模板
     * @param activityTemplate
     */
    void editTemplate(ActivityTemplate activityTemplate);

    /**
     * 修改活动模板状态
     * @param activityTemplate
     */
    void editTemplateStatus(ActivityTemplate activityTemplate);

    /**
     * 校验活动模板是否重复
     * @param templateName 活动模板名称
     * @return
     */
    ActivityTemplate checkTemplateName(@Param("templateName") String templateName);

    /**
     * 通过模板ID获取活动模板列表
     * @param templateIds 模板id集合
     * @return
     */
    List<ActivityTemplate> getTemplateByIds(@Param("templateIds") List<String> templateIds);

    /**
     * 通过模板ID获取活动模板
     * @param templateId 模板id
     * @return
     */
    ActivityTemplate getTemplateById(@Param("templateId") String templateId);

    /**
     * 通过小类型查询模板
     * @param subType
     * @return
     */
    List<ActivityTemplate> getTemplateBySubType(@Param("subType") Integer subType);
}
