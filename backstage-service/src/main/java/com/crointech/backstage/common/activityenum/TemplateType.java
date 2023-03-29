package com.crointech.backstage.common.activityenum;

import com.crointech.backstage.common.aspect.DbTypeEnumIfc;

/**
 * @author Jason Q
 * @create 2023-01-13 15:06
 * @Description 模版类型
 **/
public enum TemplateType implements DbTypeEnumIfc {

    // 赠金
    WITHGOLD(1),

    // 实物
    PHYSICAL(2);

    private final int id;

    TemplateType(int val) {
        this.id = val;
    }

    public int getId() {
        return this.id;
    }

    public static TemplateType valOf(int tu) {
        TemplateType[] values = TemplateType.values();
        for (TemplateType status : values) {
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
