package com.crointech.backstage.domain.entity.activity;

import com.crointech.backstage.common.activityenum.SubType;
import com.crointech.backstage.common.activityenum.TemplateType;
import com.crointech.croincommon.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author hp
 * @create 2021-11-10
 * @Description 活动模板实体
 **/
@Data
@ApiModel(value = "TemplateSaveReq", description = "活动模板保存请求实体")
public class ActivityTemplate extends BaseEntity {

    //模板id
    private String templateId;

    //模板名称
    private String templateName;

    //模板类型
    private TemplateType templateType;

    private SubType subType;

    //模板描述
    private String templateDesc;

    //状态（false：禁用，true：启用）
    private Boolean status;

    //是否返佣，0、否 1、是
    private Boolean commiTag;


    public ActivityTemplate(String templateId) {
        this.templateId = templateId;
    }
//
//    /**
//     * 新增实体
//     * @param userId
//     * @param req
//     */
//    public void add(Integer userId, TemplateSaveReq req) {
//        this.templateName = req.getTemplateName();
//        this.templateType = TemplateType.valOf(req.getTemplateType());
//        this.templateDesc = req.getTemplateDesc();
//        this.status = req.getStatus();
//        this.subType = SubType.valOf(req.getSubType());
//        this.commiTag = req.getCommiTag();
//        this.setRemark(req.getRemark());
//        this.setCreateUser(userId);
//        this.setCreateTime(DateUtils.getCurDate());
//        this.setOptUser(userId);
//        this.setOptTime(DateUtils.getCurDate());
//        this.setDel(Boolean.FALSE);
//    }
//
//    /**
//     * 编辑实体
//     * @param userId
//     * @param req
//     */
//    public void edit(Integer userId, TemplateEditReq req) {
//        this.status = req.getStatus();
//        this.subType = SubType.valOf(req.getSubType());
//        this.templateDesc = req.getTemplateDesc();
//        this.commiTag = req.getCommiTag();
//        this.setRemark(req.getRemark());
//        this.setOptUser(userId);
//        this.setOptTime(DateUtils.getCurDate());
//    }
//
//    /**
//     * 修改状态实体
//     * @param userId
//     * @param req
//     */
//    public void editStatus(Integer userId, TemplateStatusEditReq req) {
//        this.status = req.getStatus();
//        this.setOptUser(userId);
//        this.setOptTime(DateUtils.getCurDate());
//    }
}
