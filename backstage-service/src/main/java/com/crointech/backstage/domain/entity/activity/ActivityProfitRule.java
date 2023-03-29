package com.crointech.backstage.domain.entity.activity;

import com.crointech.croincommon.common.TimeType;
import com.crointech.croincommon.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author Jason Q
 * @create 2022-05-11 16:51
 * @Description 可盈利出金模版规则实体
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Slf4j
public class ActivityProfitRule extends ActivityRule {

    private static final long serialVersionUID = -7188525606361610248L;

    /**
     * 入金有效期 -- 固定时间
     */
    private final static Integer FIXED = 1;

    /**
     * 入金有效期 -- 累计时间
     */
    private final static Integer CUMULATIVE = 2;

    /**
     * 账户筛选（0：全部账号，1：主交易账号，2：同名账户）
     */
    private Integer accountSelect;
    /**
     * 时间类型
     */
    private Integer depositTimeModel;
    /**
    *累计入金时间单位（1：小时，2：天）    
	*/
    private Integer depositTimeType;
    /**
     * 固定入金时间
     */
    private Date depositTimeFixed;
    
    /**
     * 交易有效期类型（1：固定时间，2：入金累计时间）
     */
    private Integer tradingValidityModel;


    /**
     * 计算累计交易时间
     *
     * @param initTime    订单创建时间
     * @param defaultTime 默认时间（入金累计时间）
     * @return 累计交易时间
     */
    public Date calculateCumulativeTradeTime(Date initTime, Date defaultTime) {
        if (FIXED.equals(this.getTradingValidityModel())) {
            //固定交易有效期时间计算
            return DateUtils.getCumulativeTime(initTime, this.getTradingValidityDay(), TimeType.DAY.getId());
        } else if (CUMULATIVE.equals(this.getTradingValidityModel())) {
            //累计交易有效期
            //累计交易有效期与累计入金时间一致
            return defaultTime;
        } else {
            log.info("unknown cumulative volume time type");
        }
        return null;
    }

    /**
     * 计算累计入金时间
     *
     * @param defaultTime 默认时间（首次入金时间 || 订单创建时间）
     * @return 累计时间
     */
    public Date calculateCumulativeDepositTime(Date defaultTime) {
        if (FIXED.equals(this.getDepositTimeModel())) {
            //固定时间
            return this.getDepositTimeFixed();
        } else if (CUMULATIVE.equals(this.getDepositTimeModel())) {
            //累计时间
            Integer time = this.getDepositTime();
            return DateUtils.getCumulativeTime(defaultTime, time, this.getDepositTimeType());
        } else {
            log.info("unknown deposit time type");
        }
        return null;
    }

}
