package com.crointech.backstage.common.activityenum;

import com.crointech.croincommon.common.BusinessEnumIfc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author: Jason
 * @create: 2021-03-11 15:24
 **/
public enum ActivityBusinessMsg implements BusinessEnumIfc {

    /**
     * 调用外部接口响应Code
     */
    SUCCESS("MSCS0000", "请求成功"),
    SYS_ERROR("MSCE9999", "请求失败"),
    PARAM_UN_SUPPORT("MSAE0002", "参数不合法"),
    PARAM_ERROR("PARAM9999", "参数错误！"),
    CODE_ERROR("ERROR9999", "处理失败！"),

    /**
     * 获取当前登陆信息业务错误
     */
    TOKEN_EXPIRED("MSCE9994", "登录超时"),
    TOKEN_OFFLINE("MSCE9993", "已经在别处登录"),
    NOT_LOGIN("MSCE9992", "未登录"),
    LOGIN_NOT_EXIST("MSCE9995", "用户不存在"),
    MCLC0030("MCLC0030", "账号或者密码错误"),
    MCLC0031("MSCE9996", "抱歉，无法登录，请联系管理员"),
    MCLC0032("MSCE9997", "抱歉，无法登录，请联系管理员"),


    /**
     * 角色相关异常信息
     */
    MRLE0001("MRLE0001", "角色ID不能为空"),
    MRLE0002("MRLE0002", "角色名称已存在，请重新输入"),
    MRLE0003("MRLE0003", "用户ID不能为空"),
    MRLE0004("MRLE0004", "当前用户不存在"),
    MRLE0005("MRLE0005", "确认密码输入错误"),
    MRLE0006("MRLE0006", "当前登陆名已存在，请重新输入"),
    MRLE0007("MRLE0007", "不允许编辑当前登陆账号"),
    MRLE0008("MRLE0008", "邮箱格式错误"),
    MRLE0009("MRLE0009", "该账号隶属角色被禁用，无法启用"),
    MRLE0010("MRLE0010", "被禁用的角色不可被选择"),


    /**
     * 活动相关的异常信息
     */
    MAAE0002("MAAE0002", "输入的标题字数应小于50个字符!"),
    MAAE0003("MAAE0003", "活动标题已重复，请修改后重试!"),
    MAAE0004("MAAE0004", "活动结束时间应晚于活动开始时间!"),
    MAAE0011("MAAE0011", "此用户已报名活动!"),
    MAAE0018("MAAE0018", "请选择商户!"),
    MAAE0019("MAAE0019", "该活动模板不存在！"),
    MAAE0020("MAAE0020", "该活动不存在！"),
    MAAE0021("MAAE0021", "活动ID不能为空！"),
    MAAE0022("MAAE0022", "活动不存在规则！"),
    MAAE0023("MAAE0023", "活动已存在规则！"),
    MAAE0025("MAAE0025", "报名条件不满足！"),
    MAAE0027("MAAE0027", "交易有效期必须大于等于入金时间！"),
    MAAE0028("MAAE0028", "活动已结束！"),
    MAAE0029("MAAE0029", "存在不满足活动的交易账号，请确认！"),
    MAAE0030("MAAE0030", "交易账号不存在，请确认！"),
    MAAE0031("MAAE0031", "交易订单已被消费，不予重复处理"),
    MAAE0032("MAAE0032", "出金订单已被消费，不予重复处理"),
    MAAE0033("MAAE0033", "入金订单已被消费，不予重复处理"),
    MAAE0034("MAAE0034", "转账订单已被消费，不予重复处理"),
    MAAE0035("MAAE0035", "返佣账号不存在！"),
    MAAE0036("MAAE0036", "返佣账号已存在！"),
    MAAE0037("MAAE0037", "赠金不可抗亏损时不可抗亏损比例不能为空！"),
    MAAE0038("MAAE0038", "累计入金时入金时间不能为空！"),
    MAAE0039("MAAE0039", "指定代理时返佣账号参数错误！"),
    MAAE0040("MAAE0040", "国家不存在！"),
    MAAE0041("MAAE0041", "机构组不存在！"),
    MAAE0042("MAAE0042", "机构组不能为空！"),
    MAAE0043("MAAE0043", "交易组不存在！"),
    MAAE0044("MAAE0044", "交易组不能为空！"),
    MAAE0045("MAAE0045", "交易组server不存在！"),
    MAAE0047("MAAE0047", "账号未下发赠金，不能执行收回赠金操作"),
    MAAE0048("MAAE0048", "账号已被禁用，不能执行收回赠金操作"),


    /**
     * 活动模板相关的异常信息
     */
    MATE0001("MATE0001", "活动模板名称已存在，请重新输入！"),
    MATE0002("MATE0002", "商户号不能为空！"),


    /**
     * 活动黑名单相关的异常信息
     */
    MABL0001("MABL0001", "活动黑名单已存在，请重新输入！"),
    MABL0002("MABL0002", "活动黑名单不存在！"),
    MABL0003("MABL0003", "无此邮箱！"),
    MABL0004("MABL0004", "该账号不满足报名条件！"),

    /**
     * 接口调用异常信息
     */
    COMMONFAIL0001("COMMONFAIL0001", "调用接口结果异常!"),
    INTERF0001("INTERF0001", "调用交易账户查询接口结果异常!"),
    INTERF0002("INTERF0002", "查询用户信息异常，请联系管理员!"),
    INTERF0003("INTERF0003", "调用更新交易账号状态为只读接口异常!"),
    COMMITION_0001("COMMITION_0001", "调用佣金查询接口结果异常!"),

    MSUW0217("MSUW0217", "交易账号生成异常"),

    MSUW0215("MSUW0215", "账号序列异常"),

    /**
     * 锁异常
     */
    NO_LOCK("MSAE9997", "锁已经超时释放"),
    MCLC0004("MCLC0004", "未获取锁"),


    MESC0013("MESC0013", "该模版已被禁用!"),
    MESC0014("MESC0014", "当前交易账号未分配上级销售!"),

    /**
     * RSA加解密异常信息
     */
    RSA0001("RSA0001", "加解密异常!"),


    /**
     * 新增异常信息
     */
    MAAE0050("MAAE0050", "交易账号不存在或已被归档"),
    MAAE0051("MAAE0051", "备注超过长度限制"),
    MAAE0052("MAAE0052", "金额输出错误"),
    MAAE0053("MAAE0053", "活动不存在或已结束"),
    MAAE0054("MAAE0054", "上传文件路径异常"),
    MAAE0055("MAAE0055", "上传文件数量与活动规则定义数量不一致"),
    MAAE0056("MAAE0056", "已有特批数据处理中"),
    MAAE0057("MAAE0057", "该账号已存在，不支持重复报名"),
    MAAE0058("MAAE0058", "按比例出金时，出金比例和出金模式不能为空！"),
    MAAE0059("MAAE0059", "活动数据不存在！"),


    /**
     * 修改账号赠金接口使用异常信息
     */
    MAAE0060("MAAE0060", "交易账号未报名活动或已被禁用"),
    MAAE0061("MAAE0061", "金额必须大于0"),
    MAAE0062("MAAE0062", "金额必须小于0"),
    MAAE0063("MAAE0063", "赠金不能为负数且不能进行执行"),
    MAAE0064("MAAE0064", "输入金额超出最大限制"),
    MAAE0065("MAAE0064", "扣减金额不能超过实际金额"),

    /**
     * 2.4.2新增异常信息
     */
    MAAE0066("MAAE0066", "入金时间类型不能为空！"),
    MAAE0067("MAAE0067", "固定入金时间不能为空！"),
    MAAE0068("MAAE0068", "交易有效期不能为空！"),
    MAAE0069("MAAE0069", "层级任务格式错误！"),
    MAAE0070("MAAE0070", "首次入金时交易有效期不能选择入金累计时间！"),
    MAAE0071("MAAE0071", "活动总赠金不能小于单个账号赠金上限！"),
    MAAE0072("MAAE0072", "该账号已归档！"),
    MAAE0073("MAAE0073", "盈餘出金时，盈利出金模式不能为空！"),
    MAAE0074("MAAE0074", "活动总赠金设置和单个交易账号赠金上限不可同时为空!"),
    MAAE0075("MAAE0075", "活动总赠金设置必須為正整數!"),
    MAAE0076("MAAE0076", "活动总赠金设置最大6位數"),
    MAAE0077("MAAE0077", "单个交易账号赠金上限必須為正整數!"),
    MAAE0078("MAAE0078", "单个交易账号赠金上限最大5位數!"),
    MAAE0079("MAAE0079", "最低入金金额必須為正整數!"),
    MAAE0080("MAAE0080", "最低入金金额最大5位數!"),
    MAAE0081("MAAE0081", "亏损比例需介於1-100的正整數"),
    MAAE0082("MAAE0082", "出金比例需為正整數"),
    MAAE0083("MAAE0083", "释放金额需為正整數"),
    MAAE0084("MAAE0084", "释放金额最大五位數"),
    MAAE0085("MAAE0085", "赠金释放标准需為正整數"),
    MAAE0086("MAAE0086", "赠金释放标准最大五位數"),
    MAAE0087("MAAE0087", "层级任务最大100組"),
    MAAE0088("MAAE0088", "指定代理-返佣账号不正确"),
    MAAE0090("MAAE0090", "入金时间須為正整數"),
    MAAE0091("MAAE0091", "入金时间最大三位數"),
    MAAE0092("MAAE0092", "有效期时间須為正整數"),
    MAAE0093("MAAE0093", "有效期时间最大三位數"),
    MAAE0094("MAAE0094", "出金比例最大五位數"),
    MAAE0095("MAAE0095", "赠金金额设置須為正整數"),
    MAAE0096("MAAE0096", "赠金金额设置最大五位數"),
    MAAE0097("MAAE0097", "完成交易手数須為正整數"),
    MAAE0098("MAAE0098", "完成交易手数最大五位數"),
    MAAE0099("MAAE0099", "真實帳戶提現為固定模式時，释放金额不能为空"),
    MAAE0100("MAAE0100", "服务信息不存在"),
    MAAE0101("MAAE0101", "真實帳戶提現為同步demo帳戶的餘額時，释放金额不能为空"),
    MAAE0102("MAAE0102", "商户未配置"),
    MAAE0103("MAAE0103", "赠金下发失败"),
    MAAE0104("MAAE0104", "赠金释放失败"),
    MAAE0105("MAAE0105", "赠金回收失败"),
    ;
    private final String errorCode;

    private final String errorMsg;

    ActivityBusinessMsg(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    @Override
    public String getBusRespCode() {
        return errorCode;
    }

    @Override
    public String getRespMsg() {
        return errorMsg;
    }


    /**
     * 输出为文档
     *
     * @param args
     */
    public static void main(String[] args) {
//        validateRepeat();
        System.out.println(printToStr(Arrays.asList("MSCS", "MSCE", "MAAE")));
    }

    private final static void validateRepeat() {
        Set<String> codes = new HashSet<>();
        Set<String> msgs = new HashSet<>();
        ActivityBusinessMsg[] businessMsgs = ActivityBusinessMsg.values();
        for (ActivityBusinessMsg businessMsg : businessMsgs) {
            String busiRespCode = businessMsg.getBusRespCode();
            if (codes.contains(busiRespCode)) {
                throw new RuntimeException(String.format("重复的code: %s", busiRespCode));
            }
            codes.add(busiRespCode);
            String respMsg = businessMsg.getRespMsg();
            if (msgs.contains(respMsg)) {
                throw new RuntimeException(String.format("重复的msg: %s", respMsg));
            }
            msgs.add(respMsg);
        }
        System.out.println("没有重复的状态码！");
    }

    private final static String printToStr(List<String> ignorePrefixes) {
        ignorePrefixes = ignorePrefixes == null ? new LinkedList<>() : ignorePrefixes;
        ActivityBusinessMsg[] businessMsgs = ActivityBusinessMsg.values();
        List<ActivityBusinessMsg> businessMsgList = Arrays.asList(businessMsgs);
        businessMsgList.sort((o1, o2) -> {
            if (o1.getBusRespCode().indexOf("MSTE") == 0) {
                if (o2.getBusRespCode().indexOf("MSTE") == 0) {
                    return compareCode(o1.getBusRespCode(), o2.getBusRespCode());
                }
                return -1;
            }
            if (o2.getBusRespCode().indexOf("MSTE") == 0) {
                return 1;
            }
            return compareCode(o1.getBusRespCode(), o2.getBusRespCode());
        });
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("##### Activity项目状态码:\n\n| code码 | 描述 |\n| ----- | --- |\n");
        goFor:
        for (ActivityBusinessMsg businessMsg : businessMsgList) {
            for (String ignorePrefix : ignorePrefixes) {
                if (businessMsg.getBusRespCode().indexOf(ignorePrefix) == 0) {
                    continue goFor;
                }
            }
            stringBuffer.append("| ")
                .append(businessMsg.getBusRespCode())
                .append(" | ")
                .append(businessMsg.getRespMsg())
                .append(" |\n");
        }
        return stringBuffer.toString();
    }

    private final static int compareCode(String code1, String code2) {
        String o1Code = code1.substring(4);
        String o2Code = code2.substring(4);
        int o1CodeInt = Integer.parseInt(o1Code);
        int o2CodeInt = Integer.parseInt(o2Code);
        if (o1CodeInt < o2CodeInt) {
            return -1;
        }
        if (o1CodeInt > o2CodeInt) {
            return 1;
        }
        return 0;
    }
}
