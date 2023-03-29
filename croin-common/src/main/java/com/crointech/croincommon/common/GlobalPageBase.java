package com.crointech.croincommon.common;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 全局基础Req，提供共有tostring 和 分页参数
 * 建议查询业务中的Req都继承该类
 *
 * @author Laban
 */
public abstract class GlobalPageBase implements Serializable {

    private static final long serialVersionUID = -560604278383392569L;
    /**
     * 当前页 默认为1
     */
    @ApiModelProperty(value = "当前页码", name = "page", example = "1", required = true)
    private Integer page = 1;

    /**
     * 每页展示条数 默认为10
     */
    @ApiModelProperty(value = "每页显示条数", name = "limit", example = "10", required = true)
    private Integer limit = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    public void validate() {
        if (page == null || limit == null) {
            throw new BusinessException(GlobalBusiness.PARAM_LOSS);
        }
        if (page < 1 || limit < 1) {
            throw new BusinessException(GlobalBusiness.PARAM_UNSUPPORT);
        }
    }

    @ApiModelProperty(value = "offset", hidden = true)
    public int getOffset() {
        return page > 0 ? (page - 1) * limit : 0;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
