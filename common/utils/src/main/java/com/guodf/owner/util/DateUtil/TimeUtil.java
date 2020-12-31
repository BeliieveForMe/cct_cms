package com.guodf.owner.util.DateUtil;

import com.guodf.owner.util.BaseUtil.NumFormatCover;
import org.apache.commons.lang.StringUtils;

public class TimeUtil {
	/**
     * 毫秒转时间
     *
     * @param time
     * @return
     */
    public static String formattime(long time) {
        String hours = (time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60) + "";
        String min = (time / (1000 * 60)) + "";
        String second = (time % (1000 * 60) / 1000) + "";
        if (hours.length() < 2) {
            hours = 0 + hours;
        }
        if (min.length() < 2) {
            min = 0 + min;
        }
        if (second.length() < 2) {
            second = 0 + second;
        }
        return hours + ":" + min + ":" + second;
    }

    /**
     * 毫秒转分钟
     *
     * @return 两位小数
     */
    public static String mill2Min(String millSecond) {
        return StringUtils.isBlank(millSecond) ? "0" : NumFormatCover.getDecimalForTwo(Double.parseDouble(millSecond), 1000 * 60);
    }

    /**
     * 毫秒转秒
     *
     * @return 两位小数
     */
    public static String mill2Sec(String millSecond) {
        return StringUtils.isBlank(millSecond) ? "0" : NumFormatCover.getDecimalForTwo(Double.parseDouble(millSecond), 1000);
    }

    /**
     * 毫秒转小时
     *
     * @return 两位小数
     */
    public static String mill2Hour(String millSecond) {
        return StringUtils.isBlank(millSecond) ? "0" : NumFormatCover.getDecimalForTwo(Double.parseDouble(millSecond), 1000 * 60 * 60);
    }
}
