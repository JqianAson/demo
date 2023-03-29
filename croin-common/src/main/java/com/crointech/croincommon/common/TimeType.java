package com.crointech.croincommon.common;

/**
 * @author Jason Q
 * @create 2021-07-07 14:47
 * @Description 时间类型
 **/
public enum TimeType {

    /**
     * 累计入金时间单位
     */
    HOURS(1, "小时"),
    DAY(2, "天"),
    MIMUTES(3, "分钟");

    private int id;

    private String desc;

    TimeType(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static TimeType valOf(int anId) {
        for (TimeType value : TimeType.values()) {
            if (value.id == anId) {
                return value;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
