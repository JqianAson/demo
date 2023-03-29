package com.crointech.backstage.common.aspect;

import com.crointech.croincommon.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hp
 * @create 2021-11-05
 * @Description 操作日志
 **/
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperLog extends BaseEntity {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 操作类型
     */
    private OperType operType;

    /**
     * 操作内容
     */
    private String operContent;

    /**
     * 操作浏览器
     */
    private String operBrowser;
}