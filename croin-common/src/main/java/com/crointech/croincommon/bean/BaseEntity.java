package com.crointech.croincommon.bean;

import com.crointech.croincommon.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jason Q
 * @create 2021-10-29 19:25
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5664411601709459026L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 备注
     */
    private String remark;
    /**
     * 活动创建时间
     */
    private Date createTime;

    /**
     * 活动创建人
     */
    private Integer createUser;

    /**
     * 活动编辑时间
     */
    private Date optTime;

    /**
     * 编辑活动人
     */
    private Integer optUser;

    /**
     * 删除状态
     */
    private Boolean del;

    protected void create(Integer operId) {
        this.setCreateUser(operId);
        this.setCreateTime(DateUtils.getCurDate());
        this.setOptUser(operId);
        this.setOptTime(DateUtils.getCurDate());
        this.setDel(Boolean.FALSE);
    }

    protected void edit(Integer operId) {
        this.setOptUser(operId);
        this.setOptTime(DateUtils.getCurDate());
    }
}


