package com.crointech.backstage.domain.entity.activityaccount;

import com.crointech.croincommon.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Jason Q
 * @create 2021-10-27 11:47
 * @Description 用户
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityUser extends BaseEntity {

    private Integer userId;
    private Integer roleId;
    private String roleName;
    private String userName;
    private String loginName;
    private String password;
    private String oldPassword;
    private String email;
    private String phone;
    private Boolean status;
    private Date loginTime;
    private String ipAddress;
    private Integer loginCount;

    public ActivityUser(Integer userId) {
        this.userId = userId;
    }

    /**
     * 删除
     *
     * @param userId 当前登陆人ID
     */
    public void delete(Integer userId) {
        this.setDel(true);
        editUserInfo(userId);
    }

    /**
     * 修改用户状态
     *
     * @param userId 当前登陆人ID
     * @param status 状态
     */
    public void editUserStatus(Integer userId, Boolean status) {
        this.setStatus(status);
        editUserInfo(userId);
    }

    /**
     * 增加编辑人和编辑时间
     *
     * @param userId 当前登陆人ID
     */
    public void editUserInfo(Integer userId) {
        this.setOptTime(new Date());
        this.setOptUser(userId);
    }

    /**
     * 增加用户时要增加的创建信息
     *
     * @param userId 当前登陆人ID
     */
    public void insertUserCreateInfo(Integer userId) {
        this.setCreateUser(userId);
        this.setCreateTime(new Date());
    }
}
