package com.bfc.boot.code.common.utils;


import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.format.FastDateFormat;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * description: 时间工具类
 * date: 6/8/2022 8:10 PM
 * author: 白枫朝
 */
@Slf4j
public class DateUtils {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * description: 日期转字符串
     * date: 6/8/2022 7:14 PM
     * author: 白枫朝
     *
     * @param pattern 字符串格式
     * @param date 时间
     * @return String
     */
    public static String date2Str(String pattern, Date date) {
        FastDateFormat df = FastDateFormat.getInstance(pattern);
        String format = df.format(date);
        return format;
    }


    /**
     * description: 字符串转日期
     * date: 6/8/2022 7:14 PM
     * author: 白枫朝
     *
     * @param pattern 字符串格式
     * @param str 时间字符串
     * @return Date
     */
    public static Date str2Date(String pattern, String str) {

        FastDateFormat df = FastDateFormat.getInstance(pattern);
        try {
            Date date = df.parse(str);
            return date;
        } catch (ParseException e) {
            log.error("转换时间格式错误,原始数据为:{},格式为:{}", str, pattern);
            log.error("异常信息:{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * description: 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * date: 6/8/2022 8:02 PM
     * author: 白枫朝
     *
     * @param before 开始时间（包括）
     * @param after  结束时间（不包括）
     * @param unit   Y:年;M:月;D:日;H:时
     * @return long
     */
    public static long between(Date before, Date after, String unit) {
        LocalDateTime beforeTime = LocalDateTimeUtil.of(before);
        LocalDateTime afterTime = LocalDateTimeUtil.of(after);
        ChronoUnit chronoUnit = ChronoUnit.DAYS;
        if ("H".equals(unit)) {
            chronoUnit = ChronoUnit.HOURS;
        } else if ("M".equals(unit)) {
            chronoUnit = ChronoUnit.MONTHS;
        } else if ("Y".equals(unit)) {
            chronoUnit = ChronoUnit.YEARS;
        } else if ("D".equals(unit)) {
            chronoUnit = ChronoUnit.DAYS;
        }
        long between = LocalDateTimeUtil.between(beforeTime, afterTime, chronoUnit);
        return between;
    }

}
