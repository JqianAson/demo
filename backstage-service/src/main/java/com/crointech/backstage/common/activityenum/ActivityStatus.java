package com.crointech.backstage.common.activityenum;

/**
 * @author Jason Q
 * @create 2021-06-29 10:57
 * @Description 活动状态
 **/
public enum ActivityStatus {

    /**
     * 活动状态
     */
    SAVE(0, "已保存"),
    WAITING_START(1, "待开始"),
    ONGOING(2, "进行中"),
    END(3, "已结束");

    private final int id;
    private final String desc;

    ActivityStatus(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static ActivityStatus valOf(int anId) {
        for (ActivityStatus value : ActivityStatus.values()) {
            if (value.id == anId) {
                return value;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
}
