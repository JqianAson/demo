package com.crointech.backstage.common.dto;

import com.crointech.backstage.domain.entity.activity.Activity;
import com.crointech.backstage.domain.entity.activity.ActivityTemplate;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class ActivityDetailDto {
//
//    /**
//     * 活动id
//     */
//    private String activityId;
//
    /**
     * 活动实体
     */
    private Activity activity;
//
    /**
     * 活动模板
     */
    private ActivityTemplate activityTemplate;
//
//    /**
//     * 活动规则实体
//     */
//    private ActivityRule activityRule;
//
//    /**
//     * 活动开户规则实体
//     */
//    private ActivityOpenRule activityOpenRule;
//
//    /**
//     * 活动国家id集合
//     */
//    private List<Integer> countryIdList;
//
//    /**
//     * 活动国家集合
//     */
//    private List<ActivityCountry> countryList;
//
//    /**
//     * 活动交易组集合
//     */
//    private List<ActivityTrading> tradingList;
//
//    /**
//     * 活动交易组组装集合
//     */
//    private List<TradingServerDto> tradingServerDtoList;
//
//    /**
//     * 活动机构组Id集合
//     */
//    private List<Integer> institutionsIdList;
//
//    /**
//     * 活动机构组集合
//     */
//    private List<ActivityInstitutions> institutionsList;
//
//    /**
//     * 活动品种组集合
//     */
//    private List<ActivitySpecies> speciesList;
//
//    /**
//     * 活动品种组组装集合
//     */
//    private List<SpeciesGroupDto> speciesGroupDtoList;
//
//    /**
//     * 获取返佣账号
//     */
//    private List<Integer> commissionAccountList;
//
//    /**
//     * 補貼活動實體
//     */
//    private ActivitySubsidiesRule activitySubsidiesRule;
//
//    /**
//     * 持续活动规则实体
//     */
//    private ActivityContinueRule activityContinueRule;
//
//    /**
//     * 可盈利出金规则实体
//     */
//    private ActivityProfitRule activityProfitRule;
//
//    /**
//     * demo赠金规则实体
//     */
//    private ActivityDemoRule activityDemoRule;
//
//    public ActivityDetailDto() {
//
//    }
//
//    public ActivityDetailDto(String activityId) {
//        this.activityId = activityId;
//        //默认设置活动和模板
//        setActivityRule();
//    }
////
////    /**
////     * 设置活动（不抛异常使用）
////     *
////     * @param activityId
////     */
////    public void setActivity(String activityId) {
////        ActivityRepository activityRepository = RepositoryBuilder.instance().getFactory().createActivityRepository();
////        Activity activity = activityRepository.getActivity(activityId);
////        if (null == activity) {
////            return;
////        }
////        this.activity = activity;
////        ActivityTemplateRepository templateRepository = RepositoryBuilder.instance().getFactory().createActivityTemplateRepository();
////        ActivityTemplate template = templateRepository.getTemplateById(this.getActivity().getActivityTemplateId());
////        if (null == template) {
////            return;
////        }
////        this.activityTemplate = template;
////    }
//
////    public void setActivity() {
////        ActivityRepository activityRepository = RepositoryBuilder.instance().getFactory().createActivityRepository();
////        Activity activity = activityRepository.getActivity(this.activityId);
////        if (activity == null) {
////            throw new BusinessException(BusinessMsg.MAAE0020);
////        }
////        this.activity = activity;
////        ActivityTemplateRepository templateRepository = RepositoryBuilder.instance().getFactory().createActivityTemplateRepository();
////        ActivityTemplate template = templateRepository.getTemplateById(this.getActivity().getActivityTemplateId());
////        if (template == null) {
////            throw new BusinessException(BusinessMsg.MAAE0019);
////        }
////        this.activityTemplate = template;
////    }
//
//    public void setActivityRule() {
//
//        //TODO 更换为使用handler处理，使用subType区分
//
//
//        //1入金赠金
//        if (SubType.DEPOSIT_WITHGOLD.getId() == this.activityTemplate.getSubType().getId()) {
//            String ruleContent = this.activity.getRuleContent();
//            this.activityRule = JSON.parseObject(ruleContent, ActivityRule.class);
//        }
//        //2开户赠金
//        if (SubType.OPEN_PHYSICAL.getId() == this.activityTemplate.getSubType().getId()) {
//            String ruleContent = this.activity.getRuleContent();
//            this.activityOpenRule = JSON.parseObject(ruleContent, ActivityOpenRule.class);
//        }
//        //3补贴赠金
//        if (SubType.SUBSIDIES.getId() == this.activityTemplate.getSubType().getId()) {
//            String ruleContent = this.activity.getRuleContent();
//            this.activitySubsidiesRule = JSON.parseObject(ruleContent, ActivitySubsidiesRule.class);
//        }
//        //4持续赠金
//        if (SubType.DEPOSIT_CONTINUE.getId() == this.activityTemplate.getSubType().getId()) {
//            String ruleContent = this.activity.getRuleContent();
//            this.activityContinueRule = JSON.parseObject(ruleContent, ActivityContinueRule.class);
//        }
//        //5可盈利出金
//        if (SubType.PROFIT_WITHDRAWAL.getId() == this.activityTemplate.getSubType().getId()) {
//            String ruleContent = this.activity.getRuleContent();
//            this.activityProfitRule = JSON.parseObject(ruleContent, ActivityProfitRule.class);
//        }
//
//        //6Demo赠金
//        if (SubType.DEMO.getId() == this.activityTemplate.getSubType().getId()) {
//            String ruleContent = this.activity.getRuleContent();
//            this.activityDemoRule = JSON.parseObject(ruleContent, ActivityDemoRule.class);
//        }
//    }
//
//    public void setActivityCountry() {
//        ActivityCountryRepository countryRepository = RepositoryBuilder.instance().getFactory().createActivityCountryRepository();
//        List<ActivityCountry> countryList = countryRepository.getActivityCountry(this.activityId);
//        this.countryList = countryList;
//        if (!CollectionUtils.isEmpty(countryList))
//            this.countryIdList = countryList.stream().map(ActivityCountry::getCountryId).collect(Collectors.toList());
//    }
//
//    public void setActivityInstitutions() {
//        ActivityInstitutionsRepository institutionsRepository = RepositoryBuilder.instance().getFactory().createActivityInstitutionsRepository();
//        List<ActivityInstitutions> institutionsList = institutionsRepository.getActivityInstitutions(this.activityId);
//        this.institutionsList = institutionsList;
//        if (!CollectionUtils.isEmpty(institutionsList))
//            this.institutionsIdList = institutionsList.stream().map(ActivityInstitutions::getInstitutionsId).collect(Collectors.toList());
//    }
//
//    public void setActivityTrading() {
//        ActivityTradingRepository tradingRepository = RepositoryBuilder.instance().getFactory().createActivityTradingRepository();
//        this.tradingList = tradingRepository.getActivityTrading(this.activityId);
//        this.tradingServerDtoList = convertTradingGroup();
////        if (!CollectionUtils.isEmpty(tradingList)) {
////            this.tradingServerDtoList = convertTrading(tradingList);
////        }
//    }
//
//    public void setActivitySpecies() {
//        ActivitySpeciesRepository speciesRepository = RepositoryBuilder.instance().getFactory().createActivitySpeciesRepository();
//        List<ActivitySpecies> speciesList = speciesRepository.getActivitySpecies(this.activityId);
//        this.speciesList = speciesList;
//        if (!CollectionUtils.isEmpty(speciesList))
//            this.speciesGroupDtoList = convertSpecies(speciesList);
//    }
//
//    public void setActivityCommission() {
//        ActivityCommissionRepository commissionRepository = RepositoryBuilder.instance().getFactory().createActivityCommissionRepository();
//        List<ActivityCommission> activityCommissions = commissionRepository.getActivityCommission(this.activityId);
//        if (!CollectionUtils.isEmpty(activityCommissions)) {
//            this.commissionAccountList = activityCommissions.stream().map(ActivityCommission::getRebateAccount).collect(Collectors.toList());
//        }
//    }
//
//    /**
//     * 封装交易组响应体
//     *
//     * @param tradingList 交易组集合
//     * @return
//     */
//    private List<TradingServerDto> convertTrading(List<ActivityTrading> tradingList) {
//        List<TradingServerDto> tradingServerDtoList = Lists.newArrayList();
//        Map<Integer, List<ActivityTrading>> groupByServerId = tradingList.stream().collect(Collectors.groupingBy(ActivityTrading::getServerId));
//        for (Map.Entry<Integer, List<ActivityTrading>> entryTrading : groupByServerId.entrySet()) {
//            TradingServerDto tradingServerDto = new TradingServerDto();
//            tradingServerDto.setServerId(entryTrading.getKey());
//            if (!CollectionUtils.isEmpty(entryTrading.getValue())) {
//                List<String> tradingGroup = entryTrading.getValue().stream().map(ActivityTrading::getTradingId).collect(Collectors.toList());
//                tradingServerDto.setTradingGroup(tradingGroup);
//            }
//            tradingServerDtoList.add(tradingServerDto);
//        }
//        return tradingServerDtoList;
//    }
//
//    /**
//     * 封装品种组响应实体
//     *
//     * @param speciesList 品种组集合
//     * @return
//     */
//    private List<SpeciesGroupDto> convertSpecies(List<ActivitySpecies> speciesList) {
//        List<SpeciesGroupDto> speciesGroupDtoList = Lists.newArrayList();
//        Map<Integer, List<ActivitySpecies>> groupBySpeciesId = speciesList.stream().collect(Collectors.groupingBy(ActivitySpecies::getSpeciesId));
//        for (Map.Entry<Integer, List<ActivitySpecies>> entrySpecies : groupBySpeciesId.entrySet()) {
//            SpeciesGroupDto speciesGroupDto = new SpeciesGroupDto();
//            speciesGroupDto.setSpeciesId(entrySpecies.getKey());
//            List<SpeciesServerDto> speciesServerDtoList = Lists.newArrayList();
//            if (!CollectionUtils.isEmpty(entrySpecies.getValue())) {
//                Map<Integer, List<ActivitySpecies>> groupByServerId = entrySpecies.getValue().stream().collect(Collectors.groupingBy(ActivitySpecies::getServerId));
//                for (Map.Entry<Integer, List<ActivitySpecies>> entryServer : groupByServerId.entrySet()) {
//                    SpeciesServerDto speciesServerDto = new SpeciesServerDto();
//                    speciesServerDto.setServerId(entryServer.getKey());
//                    if (!CollectionUtils.isEmpty(entryServer.getValue())) {
//                        List<String> symbolGroup = entryServer.getValue().stream().filter(t -> !StringUtil.isBlank(t.getSymbolName())).map(ActivitySpecies::getSymbolName).collect(Collectors.toList());
//                        speciesServerDto.setSymbolGroup(symbolGroup);
//                    }
//                    speciesServerDtoList.add(speciesServerDto);
//                }
//            }
//            speciesGroupDto.setServers(speciesServerDtoList);
//            speciesGroupDtoList.add(speciesGroupDto);
//        }
//        return speciesGroupDtoList;
//    }
//
//    /**
//     * 转化交易组信息
//     *
//     * @return 转化后的交易组信息
//     */
//    private List<TradingServerDto> convertTradingGroup() {
//        String ruleContentJson = this.getActivity().getRuleContent();
//        JSONObject ruleJson = JSONObject.parseObject(ruleContentJson);
//        String tradingGroupJson = ruleJson.getString("tradingGroup");
//        return JSONArray.parseArray(tradingGroupJson, TradingServerDto.class);
//    }
}
