package com.crointech.backstage.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crointech.backstage.application.service.activity.SystemUserManagementService;
import com.crointech.backstage.common.activityenum.ActivityBusinessMsg;
import com.crointech.backstage.common.constant.ActivityConstant;
import com.crointech.backstage.domain.entity.activityaccount.ActivityUser;
import com.crointech.backstage.domain.mapper.OperLogMapper;
import com.crointech.croincommon.util.DateUtils;
import com.crointech.croincommon.util.StringUtil;
import com.crointech.croincommon.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * @Author hp
 * @create 2021/5/7 12:14 下午
 */
@Aspect
@Order(5)
@Component
@Slf4j
public class AuthAspect {

    @Autowired
    private SystemUserManagementService systemUserManagementService;

    @Autowired
    private OperLogMapper operLogMapper;

    private static List<String> addUIDUrls = new ArrayList();
    private static List<String> ignoreUrls = new ArrayList();

    private static List<String> clearToken = new ArrayList();

    static {
    }

    @Pointcut("execution(* com.crointech.backstage.controller..*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("@annotation(com.crointech.common.OperateLog)")
    public void operateLog() {
    }

    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //判断请求是什么请求
        if (request.getMethod().equalsIgnoreCase(RequestMethod.GET.name())) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, String> paramMap = new HashMap<>();
            parameterMap.forEach((key, value) -> paramMap.put(key, Arrays.stream(value).collect(joining(","))));
            log.info("请求方法-{}，请求参数（Get）:{}", request.getRequestURL(), JSON.toJSONString(paramMap));
        } else if (request.getMethod().equalsIgnoreCase(RequestMethod.POST.name()) || request.getMethod().equalsIgnoreCase(RequestMethod.PUT.name())) {
            Object[] args = joinPoint.getArgs();
            StringBuilder stringBuilder = new StringBuilder();
            Arrays.stream(args).forEach(object -> stringBuilder.append(object.toString().replace("=", ":")));
            if (stringBuilder.length() == 0) {
                stringBuilder.append("{}");
            }
            log.info("请求方法-{}，请求参数（{}}）:{}", request.getRequestURL(), request.getMethod(), JSON.toJSONString(stringBuilder));
        }

        /**
         * 获取链路id
         */
        String traceId = getTrace(request);
        MDC.clear();
        MDC.put(ActivityConstant.LOG_TRACE_KEY, traceId);
    }

    @AfterReturning(returning = "ret", pointcut = "controllerLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
//        log.info("返回 : " + JSON.toJSONString(ret));
    }

    @AfterReturning(value = "operateLog()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OperateLog opLog = method.getAnnotation(OperateLog.class);
//            log.info("当前token:{}", token);
            //除了登录登出，其他操作从token中获取用户信息保存日志
            if (opLog.operateType().getId() != OperType.LOGIN.getId() && opLog.operateType().getId() != OperType.LOGOUT.getId()) {
                // 登录信息
                Integer userId = WebUtil.getIntHeader("userId");
                ActivityUser user = systemUserManagementService.queryUserInfoById(userId);
                // 返回响应值
                String responseValue = JSON.toJSONString(keys);
                JSONObject jsonObject = JSON.parseObject(responseValue);
                String code = jsonObject.getString("code");

                // 保存日志
                OperLog operateLog = this.buildOperateLog(code, opLog.operContent(), opLog.operateType(), user);
                operLogMapper.saveOperLog(operateLog);
            }

        } catch (Exception ex) {
            log.error("AuthAspect #saveOperLog:{}", ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * 构建操作日志
     *
     * @param code           返回码
     * @param operateContent 操作内容
     * @param operateType    操作类型
     * @param user           用户信息
     */
    private OperLog buildOperateLog(String code, String operateContent, OperType operateType, ActivityUser user) {
        OperLog operLog = new OperLog();
        operLog.setOperType(operateType);
        operLog.setOperBrowser(WebUtil.getBrowserType());
        operLog.setCreateTime(DateUtils.getCurDate());
        if (null != user) {
            String result = ActivityBusinessMsg.SUCCESS.getBusRespCode().equals(code) ? "成功" : "失败";
            String context = "用户：" + user.getLoginName() + ",ip:" + WebUtil.getRemoteIp() + "," +
                DateUtils.dateToStringDefaultNewDate(DateUtils.getCurDate(), null) + " " +
                operateContent + result;
            operLog.setOperContent(context);
            operLog.setLoginName(user.getLoginName());
            operLog.setUserId(user.getId());
            operLog.setCreateUser(user.getId());
            operLog.setOptUser(user.getId());
            operLog.setOptTime(DateUtils.getCurDate());
        }
        return operLog;
    }

    private String getTrace(HttpServletRequest request) {
        String traceId = request.getParameter(ActivityConstant.LOG_TRACE_KEY);
        if (StringUtil.isEmpty(traceId)) {
            traceId = request.getHeader(ActivityConstant.LOG_TRACE_KEY);
        }
        if (StringUtil.isEmpty(traceId)) {
            traceId = ActivityConstant.LOG_PROJECT_KEY + StringUtil.generateRandom();
        }
        return traceId;
    }
}
