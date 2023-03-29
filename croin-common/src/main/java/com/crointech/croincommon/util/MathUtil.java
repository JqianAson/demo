package com.crointech.croincommon.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Jason
 * @date 2020/12/28 5:12 下午
 * @description
 **/
@Slf4j
public final class MathUtil {

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;


    public static BigDecimal mulKeepTwoDecimal(BigDecimal source, BigDecimal... source2) {
        for (BigDecimal bigDecimal : source2) {
            source = source.multiply(bigDecimal);
        }
        return source.setScale(TWO, RoundingMode.DOWN);
    }

    public static BigDecimal addKeepTwoDecimal(BigDecimal source, BigDecimal... source2) {
        for (BigDecimal bigDecimal : source2) {
            source = source.add(bigDecimal);
        }
        return source.setScale(TWO, RoundingMode.DOWN);
    }

    /**
     * 比较大小 （numA 大于 B 返回 true）
     *
     * @param numA 数字A
     * @param numB 数字B
     * @return 比较结果
     */
    public static Boolean isGreaterThan(BigDecimal numA, BigDecimal numB) {
        if (null == numA || null == numB) {
            throw new IllegalArgumentException("number Can't be empty");
        }
        return numA.compareTo(numB) > 0;
    }

    /**
     * 比较大小 （numA 小于 B 返回 true）
     *
     * @param numA 数字A
     * @param numB 数字B
     * @return 比较结果
     */
    public static Boolean lessThan(BigDecimal numA, BigDecimal numB) {
        if (null == numA || null == numB) {
            throw new IllegalArgumentException("number Can't be empty");
        }
        return numA.compareTo(numB) < 0;
    }

    /**
     * 比较大小 （numA 等于 B 返回 true）
     *
     * @param numA 数字A
     * @param numB 数字B
     * @return 比较结果
     */
    public static Boolean equal(BigDecimal numA, BigDecimal numB) {
        if (null == numA || null == numB) {
            throw new IllegalArgumentException("number Can't be empty");
        }
        return numA.compareTo(numB) == 0;
    }

    /**
     * 比较大小 （numA 大于等于 B 返回 true）
     *
     * @param numA 数字A
     * @param numB 数字B
     * @return 比较结果
     */
    public static Boolean isGreaterThanAndEqual(BigDecimal numA, BigDecimal numB) {
        if (null == numA || null == numB) {
            throw new IllegalArgumentException("number Can't be empty");
        }
        return numA.compareTo(numB) > -1;
    }

    /**
     * 比较大小 （numA 小于等于 B 返回 true）
     *
     * @param numA 数字A
     * @param numB 数字B
     * @return 比较结果
     */
    public static Boolean lessThanAndEqual(BigDecimal numA, BigDecimal numB) {
        if (null == numA || null == numB) {
            throw new IllegalArgumentException("number Can't be empty");
        }
        return numA.compareTo(numB) < 1;
    }

    /**
     * 转换数值（例如传入-1，转后后结果为1）
     *
     * @param originalNum 原始数值
     * @return 转换后数值
     */
    public static BigDecimal convertNum(BigDecimal originalNum) {

        if (null == originalNum) {
            originalNum = BigDecimal.ZERO;
        }

        return originalNum.multiply(BigDecimal.valueOf(-1));
    }
}
