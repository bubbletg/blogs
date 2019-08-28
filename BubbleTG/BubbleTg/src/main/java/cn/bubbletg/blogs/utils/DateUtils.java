package cn.bubbletg.blogs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 日期工具类MailUtils
 * @author BubbleTg
 */
public class DateUtils {

    /**
     * 日期转换成字符串
     *
     * @param date
     * @param patt
     * @return
     */
 static String dateToString(Date date, String patt) {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @param patt
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String str, String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }
}
