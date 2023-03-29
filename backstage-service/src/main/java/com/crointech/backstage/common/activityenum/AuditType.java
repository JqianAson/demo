package com.crointech.backstage.common.activityenum;

/**
 * @author Jason Q
 * @create 2021-11-12 18:33
 * @Description 审核状态
 **/
public enum AuditType {

    /**
     * 审核状态
     */
    AUDIT(0, "待审核"),
    APPROVED(1, "审核通过"),
    AUDITREJECT(2, "审核拒绝"),
    ;


    private final Integer id;

    private final String desc;

    AuditType(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static AuditType valOf(int anId) {
        for (AuditType value : AuditType.values()) {
            if (value.id == anId) {
                return value;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }


    public String getDesc() {
        return desc;
    }
}
