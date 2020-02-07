package org.platform.quartz.utils.date;

import org.platform.quartz.utils.langs.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.utils.date
 * @Description: 日期
 * @date 2020/2/6 18:27
 */
public class DateUitls {

    public static final String DEFAULT_FORMAT =  "yyyy-MM-dd HH:mm:ss";

    public static String getDateTime(Date date, String format) {
        if(StringUtils.isBlank(format) || Objects.isNull(date)) {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getDateTime() {
        return getDateTime(new Date(), DEFAULT_FORMAT);
    }

    public static String getDate(Date date) {
        return getDateTime(date, "yyyy-MM-dd");
    }

    public static String getDate() {
        return getDateTime(new Date(), "yyyy-MM-dd");
    }
}
