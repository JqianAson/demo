package com.crointech.backstage.domain.mapper;

import com.crointech.backstage.common.aspect.OperLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hp
 * @create 2021-11-05
 * @Description
 **/
@Mapper
public interface OperLogMapper {

    /**
     * 查询操作日志
     * @return
     * @param loginName 登录名
     */
    List<OperLog> getOperLog(@Param("loginName") String loginName);

    /**
     * 保存操作日志
     * @param operLog 操作日志
     */
    void saveOperLog(OperLog operLog);
}
