package com.crointech.backstage.common.activityenum;


import com.crointech.backstage.common.aspect.DbTypeEnumIfc;

/**
 * @author Jason Q
 * @create 2022-01-14 12:40
 * @Description 赠金模版类型
 **/
public enum SubType implements DbTypeEnumIfc {

    /**
     * 模版类型
     */
    DEPOSIT_WITHGOLD(1, "入金赠金"),

    OPEN_PHYSICAL(2, "开户赠金"),

    SUBSIDIES(3, "补贴赠金"),

    DEPOSIT_CONTINUE(4, "持续赠金"),

    PROFIT_WITHDRAWAL(5, "可盈利出金"),

    DEMO(6, "Demo赠金");

    private final int id;

    private final String desc;

    SubType(int val, String desc) {
        this.id = val;
        this.desc = desc;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return desc;
    }

    public static SubType valOf(int tu) {
        SubType[] values = SubType.values();
        for (SubType status : values) {
            if (status.id == tu) {
                return status;
            }
        }
        return null;
    }

    @Override
    public int getIndex() {
        return id;
    }
}
