package com.crointech.backstage.application.handler;

import com.crointech.backstage.common.activityenum.SubType;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author He
 * @Description 获取marketing业务执行器的工厂类/包含获取、注册方法
 */
@Slf4j
public class MarketingHandlerFactory {

    private static final Map<SubType, MarketingHandler> HANDLER_MAP = Maps.newConcurrentMap();

    /**
     * 根据subType获取handler类
     *
     * @param subType 模版类型
     * @return 模版处理器
     */
    public static MarketingHandler getMarketingHandler(SubType subType) {
        log.info("MarketingHandlerFactory #getMarketingHandler #subType:{} #subTypeName:{}", subType.getId(), subType.getDesc());
        return HANDLER_MAP.get(subType);
    }

    /**
     * 注册handler类
     *
     * @param subType 模版类型
     * @param handler 模版处理器
     */
    public static void register(SubType subType, MarketingHandler handler) {
        log.info("MarketingHandlerFactory #register #subType:{} #handler:{}", subType.getDesc(), handler.getClass().getSimpleName());
        if (null == subType || null == handler) {
            return;
        }
        HANDLER_MAP.put(subType, handler);
    }
}
