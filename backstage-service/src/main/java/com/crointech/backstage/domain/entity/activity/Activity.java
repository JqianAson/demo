package com.crointech.backstage.domain.entity.activity;

import com.crointech.backstage.common.activityenum.ActivityBusinessMsg;
import com.crointech.backstage.common.activityenum.ActivityStatus;
import com.crointech.backstage.common.activityenum.AuditType;
import com.crointech.croincommon.bean.BaseEntity;
import com.crointech.croincommon.common.BusinessException;
import com.crointech.croincommon.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Jason Q
 * @create 2021-10-26 20:22
 * @Description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity extends BaseEntity {

    private static final long serialVersionUID = -1297391603205032976L;
    /**
     * 活动ID
     */
    private String activityId;

    /**
     * 商户号
     */
    private String merchantsId;

    /**
     * 活动模板id
     */
    private String activityTemplateId;

    /**
     * 活动状态
     */
    private ActivityStatus status;

    /**
     * 是否可见
     */
    private Boolean visible;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动Banner
     */
    private String banner;

    /**
     * M站活动Banner
     */
    private String smallBanner;

    /**
     * 活动Url地址
     */
    private String url;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 审核状态 0、待审核 1、审核通过 2、审核不通过
     */
    private AuditType auditStatus;

    /**
     * 审核人
     */
    private Integer auditUser;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 活动是否重叠
     */
    private Boolean overlap;

    /**
     * 活动实际结束时间
     */
    private Date actualEndTime;

    /**
     * 活动代码（当前无作用，用来以后扩展）
     */
    private String activityCode;

    /**
     * 规则内容，用来存储规则配置字符串
     */
    private String ruleContent;
//
//    /**
//     * 构造商户id和模板id实体
//     *
//     * @param merchantsId 商户id
//     * @param templateId  模板id
//     */
//    public Activity(Integer merchantsId, String templateId) {
//        if (merchantsId == null) {
//                throw new BusinessException(ActivityBusinessMsg.MAAE0018);
//        }
//        if (templateId == null) {
//            throw new BusinessException(ActivityBusinessMsg.MAAE0019);
//        }
//        this.merchantsId = new MerchantsId(merchantsId);
//        this.activityTemplateId = templateId;
//    }

    /**
     * 校验参数
     *
     * @param startTime 活动开始时间
     * @param endTime   活动结束时间
     * @param overlap
     */
    public void checkInsertReq(String startTime, String endTime, List<Integer> overlap) {
        if (overlap == null) {
            throw new BusinessException(ActivityBusinessMsg.PARAM_ERROR);
        }
        //校验活动开始时间必须早于结束时间
        long startDate = DateUtils.stringToDate(startTime).getTime();
        long endDate = DateUtils.stringToDate(endTime).getTime();
        if (startDate > endDate) {
            throw new BusinessException(ActivityBusinessMsg.MAAE0004);
        }

        if (endDate == startDate) {
            throw new BusinessException(ActivityBusinessMsg.MAAE0004);
        }
    }

    /**
     * 補貼
     * 校验参数
     *
     * @param startTime 活动开始时间
     * @param endTime   活动结束时间
     * @param overlap
     */
    public void checkInsertSubsidiesReq(String startTime, String endTime, Boolean overlap) {
        if (overlap == null) {
            throw new BusinessException(ActivityBusinessMsg.PARAM_ERROR);
        }
        //校验活动开始时间必须早于结束时间
        long startDate = DateUtils.stringToDate(startTime).getTime();
        long endDate = DateUtils.stringToDate(endTime).getTime();
        if (startDate > endDate) {
            throw new BusinessException(ActivityBusinessMsg.MAAE0004);
        }

        if (endDate == startDate) {
            throw new BusinessException(ActivityBusinessMsg.MAAE0004);
        }
    }

    /**
     * 校验参数
     *
     * @param startTime 活动开始时间
     * @param endTime   活动结束时间
     */
    public void checkEditReq(String startTime, String endTime, List<Integer> overlap) {
        if (overlap == null) {
            throw new BusinessException(ActivityBusinessMsg.PARAM_ERROR);
        }
        //校验活动开始时间必须早于结束时间
        long startDate = DateUtils.stringToDate(startTime).getTime();
        long endDate = DateUtils.stringToDate(endTime).getTime();
        if (startDate > endDate) {
            throw new BusinessException(ActivityBusinessMsg.MAAE0004);
        }
    }

    /**
     * 補貼
     * 校验参数
     *
     * @param startTime 活动开始时间
     * @param endTime   活动结束时间
     */
    public void checkEditSubsidiesReq(String startTime, String endTime, Boolean overlap) {
        if (overlap == null) {
            throw new BusinessException(ActivityBusinessMsg.PARAM_ERROR);
        }
        //校验活动开始时间必须早于结束时间
        long startDate = DateUtils.stringToDate(startTime).getTime();
        long endDate = DateUtils.stringToDate(endTime).getTime();
        if (startDate > endDate) {
            throw new BusinessException(ActivityBusinessMsg.MAAE0004);
        }
    }
//
//    /**
//     * 获取新增实体
//     *
//     * @param req    新增请求参数
//     * @param userId 当前登录用户id
//     */
//    public void insertPage(PageInsertReq req, Integer userId) {
//        setOverlap(req.getOverlap() == null ? Boolean.FALSE : req.getOverlap().size() != 0);
//        setStatus(ActivityStatus.SAVE);
//        setVisible(Boolean.TRUE);
//        setTitle(req.getTitle());
//        setBanner(req.getBanner());
//        setSmallBanner(req.getSmallBanner());
//        setUrl(req.getUrl());
//        setStartTime(DateUtils.stringToDate(req.getStartTime()));
//        setEndTime(DateUtils.stringToDate(req.getEndTime()));
//        setActualEndTime(DateUtils.getSpecifyTimeAfterDate(DateUtils.stringToDate(req.getEndTime()), 1));
//        setCreateTime(DateUtils.getCurDate());
//        setCreateUser(userId);
//        setRemark(req.getRemark());
//        setOptUser(userId);
//        setOptTime(DateUtils.getCurDate());
//        setDel(Boolean.FALSE);
//        setActivityId(IdUtils.getId());
//    }
//
//    /**
//     * 補貼
//     * 获取新增实体
//     *
//     * @param req    新增请求参数
//     * @param userId 当前登录用户id
//     */
//    public void insertSubsidiesPage(SubsidiesPageReq req, Integer userId) {
//        setOverlap(req.getOverlap());
//        setStatus(ActivityStatus.SAVE);
//        setVisible(Boolean.TRUE);
//        setTitle(req.getTitle());
//        setStartTime(DateUtils.stringToDate(req.getStartTime()));
//        setEndTime(DateUtils.stringToDate(req.getEndTime()));
//        setActualEndTime(DateUtils.getSpecifyTimeAfterDate(DateUtils.stringToDate(req.getEndTime()), 1));
//        setCreateTime(DateUtils.getCurDate());
//        setCreateUser(userId);
//        setRemark(req.getRemark());
//        setOptUser(userId);
//        setOptTime(DateUtils.getCurDate());
//        setDel(Boolean.FALSE);
//        setActivityId(IdUtils.getId());
//    }
//
//    /**
//     * 编辑进行中活动页面
//     *
//     * @param req    参数
//     * @param userId 当前用户ID
//     */
//    public void editOngoingPage(EditActivityReq req, Integer userId) {
//        this.title = req.getTitle();
//        this.banner = req.getBanner();
//        this.smallBanner = req.getSmallBanner();
//        this.url = req.getUrl();
//        this.endTime = DateUtils.stringToDate(req.getEndTime());
//        this.actualEndTime = DateUtils.getSpecifyTimeAfterDate(DateUtils.stringToDate(req.getEndTime()), 1);
//        this.setRemark(req.getRemark());
//        this.setOptTime(DateUtils.getCurDate());
//        this.setOptUser(userId);
//    }
//
//    /**
//     * 補貼
//     * 编辑进行中活动页面
//     *
//     * @param req    参数
//     * @param userId 当前用户ID
//     */
//    public void editOngoingSubsidiesPage(EditSubsidiesReq req, Integer userId) {
//        this.title = req.getTitle();
//        this.endTime = DateUtils.stringToDate(req.getEndTime());
//        this.actualEndTime = DateUtils.getSpecifyTimeAfterDate(DateUtils.stringToDate(req.getEndTime()), 1);
//        this.setRemark(req.getRemark());
//        this.setOptTime(DateUtils.getCurDate());
//        this.setOptUser(userId);
//    }
//
//    /**
//     * 编辑待开始或已保存的活动页面
//     *
//     * @param req    参数
//     * @param userId 当前用户ID
//     */
//    public void editAllPage(EditActivityReq req, Integer userId) {
//        editOngoingPage(req, userId);
//        this.overlap = req.getOverlap().size() != 0;
//        this.startTime = DateUtils.stringToDate(req.getStartTime());
//    }
//
//    /**
//     * 補貼
//     * 编辑待开始或已保存的活动页面
//     *
//     * @param req    参数
//     * @param userId 当前用户ID
//     */
//    public void editAllSubsidiesPage(EditSubsidiesReq req, Integer userId) {
//        editOngoingSubsidiesPage(req, userId);
//        this.overlap = req.getOverlap();
//        this.startTime = DateUtils.stringToDate(req.getStartTime());
//    }
//
//    /**
//     * 编辑可视状态实体创建
//     *
//     * @param userId     当前用户ID
//     * @param visible    可视状态
//     * @param activityId 活动状态
//     */
//    public void editVisible(Integer userId, Boolean visible, String activityId) {
//        this.setVisible(visible);
//        this.setActivityId(activityId);
//        this.setOptTime(DateUtils.getCurDate());
//        this.setOptUser(userId);
//    }
//
//    /**
//     * 修改活动状态
//     *
//     * @param userId
//     * @param status
//     * @param activityId
//     */
//    public void editStatus(Integer userId, ActivityStatus status, String activityId) {
//        this.setStatus(status);
//        this.setActivityId(activityId);
//        this.setOptTime(DateUtils.getCurDate());
//        this.setOptUser(userId);
//    }
//
//    /**
//     * 获得指定活动已使用赠金总数
//     *
//     * @return 赠金总和
//     */
//    public BigDecimal getActivityCreditSum() {
//        RepositoryFactory factory = RepositoryBuilder.instance().getFactory();
//        ActivityAccountRepository activityAccountRepository = factory.createActivityAccountRepository();
//        //计算当前活动所有交易账号获得的赠金总和
//        List<BigDecimal> creditList = activityAccountRepository.loadAllAccountCredit(this.getMerchantsId(),
//                this.getActivityId());
//        BigDecimal amountSum = BigDecimal.ZERO;
//        for (BigDecimal credit : creditList) {
//            amountSum = amountSum.add(credit);
//        }
//        return amountSum;
//    }
}
