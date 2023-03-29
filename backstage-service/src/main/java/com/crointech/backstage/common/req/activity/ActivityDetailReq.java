package com.crointech.backstage.common.req.activity;

import com.crointech.backstage.common.activityenum.ActivityBusinessMsg;
import com.crointech.croincommon.common.BusinessException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author hp
 * @create 2021-01-16
 * @Description 活动详情请求体
 **/
@Data
@ApiModel(value = "ActivityDetailReq", description = "活动详情请求体")
public class ActivityDetailReq {

    private static final long serialVersionUID = 1446452713857383383L;

    @ApiModelProperty(value = "活动ID", name = "activityId", required = true)
    @NotEmpty
    private String activityId;

    /**
     *
     */
    public void checkReq() {
        if (this.activityId == null) {
            throw new BusinessException(ActivityBusinessMsg.MAAE0021);
        }
    }

}
