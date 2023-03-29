package com.crointech.backstage.controller.activity;

import com.crointech.backstage.application.service.activity.ActivityService;
import com.crointech.backstage.common.req.activity.ActivityDetailReq;
import com.crointech.backstage.common.req.activity.ActivityReq;
import com.crointech.backstage.common.resp.activity.ActivityDetailResp;
import com.crointech.backstage.common.resp.activity.ActivityResp;
import com.crointech.croincommon.common.DomainPageableEntity;
import com.crointech.croincommon.common.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Jason Q
 * @create 2023-01-13 14:53
 * @Description 活动相关
 **/
@RestController
@RequestMapping("/activity")
@Api(tags = "活动相关")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    @GetMapping("/list")
    @ApiOperation(value = "活动列表", notes = "活动列表")
    public Resp<DomainPageableEntity<ActivityResp>> list(ActivityReq req) {
        return Resp.createSuccessResp(activityService.queryActivityList(req));
    }

    @GetMapping("/find")
    @ApiOperation(value = "查看", notes = "查看")
    public Resp<ActivityDetailResp> find(@Valid ActivityDetailReq req) {
        ActivityDetailResp activityDetailResp = activityService.findDetail(req);
        return Resp.createSuccessResp(activityDetailResp);
    }
//
//    @OperateLog(operContent = "活动创建-新增活动页面", operateType = OperType.ADD)
//    @PostMapping("/addPage")
//    @ApiOperation(value = "新增活动页面", notes = "新增活动页面")
//    public Resp<String> addPage(@RequestBody @Valid PageInsertReq req) {
//        String activityId = activityService.insertPage(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp(activityId);
//    }
//
//    @OperateLog(operContent = "活动创建-新增规则页面", operateType = OperType.ADD)
//    @PostMapping("/addRule")
//    @ApiOperation(value = "新增规则页面", notes = "新增活动页面")
//    public Resp<Void> addRule(@RequestBody @Valid RuleInsertReq req) {
//        activityService.insertRule(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @OperateLog(operContent = "活动创建-新增开户规则", operateType = OperType.ADD)
//    @PostMapping("/addOpenRule")
//    @ApiOperation(value = "新增开户规则", notes = "新增开户规则")
//    public Resp<Void> addOpenRule(@RequestBody @Valid OpenRuleInsertReq req) {
//        activityService.insertOpenRule(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @OperateLog(operContent = "活动创建-编辑开户规则", operateType = OperType.ADD)
//    @PutMapping("/editOpenRule")
//    @ApiOperation(value = "编辑开户规则", notes = "编辑开户规则")
//    public Resp<Void> editOpenRule(@RequestBody @Valid OpenRuleInsertReq req) {
//        activityService.editOpenRule(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @OperateLog(operContent = "活动创建-新增持续赠金规则页面", operateType = OperType.ADD)
//    @PostMapping("/addContinueRule")
//    @ApiOperation(value = "新增持续赠金规则页面", notes = "新增持续赠金规则页面")
//    public Resp<Void> addContinueRule(@RequestBody @Valid ContinueRuleInsertReq req) {
//        activityService.insertContinueRule(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @OperateLog(operContent = "活动创建-编辑持续赠金规则", operateType = OperType.UPDATE)
//    @PutMapping("/editContinueRule")
//    @ApiOperation(value = "编辑持续赠金规则", notes = "编辑持续赠金规则")
//    public Resp<Void> editContinueRule(@RequestBody @Valid ContinueRuleInsertReq req) {
//        activityService.editContinueRule(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @OperateLog(operContent = "活动创建-编辑活动", operateType = OperType.UPDATE)
//    @PutMapping("/editPage")
//    @ApiOperation(value = "编辑活动", notes = "编辑活动")
//    public Resp<Void> editPage(@RequestBody @Valid EditActivityReq req) {
//        activityService.editPage(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @OperateLog(operContent = "活动创建-编辑规则", operateType = OperType.UPDATE)
//    @PutMapping("/editRule")
//    @ApiOperation(value = "编辑规则", notes = "编辑规则")
//    public Resp<Void> editRule(@RequestBody @Valid RuleInsertReq req) {
//        activityService.editRule(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @OperateLog(operContent = "活动创建-白名单", operateType = OperType.UPDATE)
//    @PostMapping("/specialApply")
//    @ApiOperation(value = "白名单", notes = "白名单")
//    public Resp<Void> specialApply(@RequestBody @Valid SpecialApplyReq req) {
//        activityService.specialApply(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @OperateLog(operContent = "活动创建-编辑可视状态", operateType = OperType.UPDATE)
//    @PutMapping("/editVisible")
//    @ApiOperation(value = "编辑可视状态", notes = "编辑可视状态")
//    public Resp<Void> editVisible(@RequestBody @Valid EditStatusReq req) {
//        activityService.editVisible(getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @GetMapping("/riskSelectList")
//    @ApiOperation(value = "所有已开始活动下拉列表", notes = "所有已开始活动下拉列表")
//    public Resp<List<ActivitySelectResp>> riskSelectList() {
//        return Resp.createSuccessResp(activityService.riskSelectList(getMerchantsId()));
//    }
//
//    @GetMapping("/notEndSelectList")
//    @ApiOperation(value = "未结束活动下拉列表", notes = "未结束活动下拉列表")
//    public Resp<List<ActivitySelectResp>> notEndSelectList() {
//        return Resp.createSuccessResp(activityService.notEndSelectList(getMerchantsId()));
//    }
//
//    @GetMapping("/getRebateAccount")
//    @ApiOperation(value = "查询返佣账号是否存在", notes = "查询返佣账号是否存在")
//    public Resp<Boolean> getRebateAccount(@Valid RebateAccountReq req) {
//        return Resp.createSuccessResp(activityService.getRebateAccount(getMerchantsId(), req));
//    }
//
//    @GetMapping("/findAllActivity")
//    @ApiOperation(value = "获取全部活动", notes = "获取全部活动")
//    public Resp<List<FindAllActivityResp>> findAllActivity() {
//        return Resp.createSuccessResp(activityService.findAllActivity(getMerchantsId()));
//    }
//
//    @OperateLog(operContent = "盈餘新增规则", operateType = OperType.ADD)
//    @PostMapping("/addProfitRule")
//    @ApiOperation(value = "盈餘新增规则", notes = "盈餘新增规则")
//    public Resp<Void> addProfitRule(@RequestBody @Valid ProfitRuleUpsertReq req) {
//        activityService.insertProfitRule(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @OperateLog(operContent = "盈餘编辑规则", operateType = OperType.UPDATE)
//    @PutMapping("/editProfitRule")
//    @ApiOperation(value = "盈餘编辑规则", notes = "盈餘编辑规则")
//    public Resp<Void> editProfitRule(@RequestBody @Valid ProfitRuleUpsertReq req) {
//        activityService.editProfitRule(getMerchantsId(), getUserId(), req);
//        return Resp.createSuccessResp();
//    }
//
//    @PostMapping("/supplementary")
//    @ApiOperation(value = "补充活动规则-释放类型", notes = "补充活动规则-释放类型")
//    public Resp<Void> supplementaryRule() {
//        activityService.supplementaryRule();
//        return Resp.createSuccessResp();
//    }
}
