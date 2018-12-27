package com.example.daily.common;

import java.util.*;

/**
 * @Auther: Zhiyuan Cui
 * @Project: crm-system
 * @Date: 2018/12/25 14:19
 * @Description:
 */
public class ConsUtil {
    /**
     * 通过身份证号码获取出生日期、性别、年龄
     *
     * @param certificateNo
     * @return 返回的出生日期格式：1990-01-01   性别格式：F-女，M-男
     */
    public static Map<String, String> getBirAgeSex(String certificateNo) {
        String birthday = "";
        String age = "";
        String gender = "";

        if (certificateNo == null) {
            return new HashMap<String, String>();
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = certificateNo.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            birthday = "19" + certificateNo.substring(6, 8) + "-"
                    + certificateNo.substring(8, 10) + "-"
                    + certificateNo.substring(10, 12);
            gender = getGenderName(Integer.parseInt(certificateNo.substring(certificateNo.length() - 3, certificateNo.length())) % 2);
            age = (year - Integer.parseInt("19" + certificateNo.substring(6, 8))) + "";
        } else if (flag && certificateNo.length() == 18) {
            birthday = certificateNo.substring(6, 10) + "-"
                    + certificateNo.substring(10, 12) + "-"
                    + certificateNo.substring(12, 14);
            gender = getGenderName(Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2);
            age = (year - Integer.parseInt(certificateNo.substring(6, 10))) + "";
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("birthday", birthday);
        map.put("age", age);
        map.put("gender", gender);
        return map;
    }

    public static String getAreaCodeByIdcard(String certificateNo) {
        if (certificateNo != null) {
            return certificateNo.substring(0, 6);
        }
        return null;
    }

    /**
     * 70前 （-1969.12.31）
     * 70后(1970.1.1-1979.12.31)
     * 80后(1980.1.1-1984.12.31)
     * 85后(1985.1.1-1989.12.31)
     * 90后(1990.1.1-1994.12.31)
     * 95后(1995.1.1-1999.12.31)
     * 00后（2000.1.1-至今）
     *
     * @param birthday
     * @return
     */
    public static String getAgeBracketByBirth(Date birthday) {
        String age = "未知";
        if (birthday == null) {
            System.out.println("根据生日(yyyy-MM-dd)判断年龄段时, 生日信息为空");
            return age;
        }
        Long current = birthday.getTime();
        if (current < DateUtil.strToDate("1970-01-01").getTime()) {
            age = "70前";
        } else if (current >= DateUtil.strToDate("1970-01-01").getTime()
                && current <= DateUtil.strToDate("1979-12-31").getTime()) {
            age = "70后";
        } else if (current >= DateUtil.strToDate("1980-01-01").getTime()
                && current <= DateUtil.strToDate("1984-12-31").getTime()) {
            age = "80后";
        } else if (current >= DateUtil.strToDate("1985-01-01").getTime()
                && current <= DateUtil.strToDate("1989-12-31").getTime()) {
            age = "85后";
        } else if (current >= DateUtil.strToDate("1990-01-01").getTime()
                && current <= DateUtil.strToDate("1994-12-31").getTime()) {
            age = "90后";
        } else if (current >= DateUtil.strToDate("1995-01-01").getTime()
                && current <= DateUtil.strToDate("1999-12-31").getTime()) {
            age = "95后";
        } else if (current >= DateUtil.strToDate("1995-01-01").getTime()) {
            age = "00后";
        } else {
            age = "未知";
        }
        return age;
    }

    /**
     * 性别标签转换
     *
     * @param gender
     * @return
     */
    public static String getGenderName(Integer gender) {
        String genderName = "未知";
        if (gender == null) {
            return genderName;
        }
        switch (gender) {
            case 1:
                genderName = "男";
                break;
            case 2:
                genderName = "女";
                break;
            default:
                genderName = "未知";
        }
        return genderName;
    }

    /**
     * 根据生日计算星座标签内容
     * 白羊座：3月21日 - 4月20日
     * 金牛座：4月21日 - 5月21日
     * 双子座：5月22日 - 6月21日
     * 巨蟹座：6月22日 - 7月22日
     * 狮子座：7月23日 - 8月23日
     * 处女座：8月24日 - 9月23日
     * 天秤座：9月24日 - 10月23日
     * 天蝎座：10月24日 - 11月22日
     * 射手座：11月23日 - 12月21日
     * 魔羯座：12月22日 - 1月20日
     * 水瓶座：1月21日 - 2月19日
     * 双鱼座：2月20日 - 3月20日
     *
     * @param birthday
     * @return
     */
    public static String getConstellation(Date birthday) {
        String constellation = "未知";
        if (birthday == null) {
            System.out.println("根据生日(yyyy-MM-dd)判断星座时, 生日信息为空");
            return constellation;
        }
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(birthday);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        System.out.println(year + ">>>>" + month + ">>>" + day);
        if ((month == 3 && day >= 21 && day <= 31) || (month == 4 && day >= 1 && day <= 19)) {
            constellation = "白羊座";
        }
        if ((month == 4 && day >= 20 && day <= 30) || (month == 5 && day >= 1 && day <= 20)) {
            constellation = "金牛座";
        }
        if ((month == 5 && day >= 21 && day <= 31) || (month == 6 && day >= 1 && day <= 21)) {
            constellation = "双子座";
        }
        if ((month == 6 && day >= 22 && day <= 30) || (month == 7 && day >= 1 && day <= 22)) {
            constellation = "巨蟹座";
        }
        if ((month == 7 && day >= 23 && day <= 31) || (month == 8 && day >= 1 && day <= 22)) {
            constellation = "狮子座";
        }
        if ((month == 8 && day >= 23 && day <= 31) || (month == 9 && day >= 1 && day <= 22)) {
            constellation = "处女座";
        }
        if ((month == 9 && day >= 23 && day <= 30) || (month == 10 && day >= 1 && day <= 23)) {
            constellation = "天秤座";
        }
        if ((month == 10 && day >= 24 && day <= 31) || (month == 11 && day >= 1 && day <= 22)) {
            constellation = "天蝎座";
        }
        if ((month == 11 && day >= 23 && day <= 30) || (month == 12 && day >= 1 && day <= 21)) {
            constellation = "射手座";
        }
        if ((month == 12 && day >= 22 && day <= 31) || (month == 1 && day >= 1 && day <= 19)) {
            constellation = "摩羯座";
        }
        if ((month == 1 && day >= 20 && day <= 31) || (month == 2 && day >= 1 && day <= 18)) {
            constellation = "水瓶座";
        }
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) { // 闰年2月29天
            if ((month == 2 && day >= 19 && day <= 29) || (month == 3 && day >= 1 && day <= 20)) {
                constellation = "双鱼座";
            }
        } else { // 平年2月28天
            if ((month == 2 && day >= 19 && day <= 28) || (month == 3 && day >= 1 && day <= 20)) {
                constellation = "双鱼座";
            }
        }
        return constellation;
    }

    /**
     * 当前时间距离婚期剩余月数，四舍五入
     * =距离的天数/30
     * 有准确日期才算
     * <p>
     * 婚期已过赋值为-1
     * <p>
     * 婚期不详（例如年份+上半年）赋值为99
     *
     * @param weddingDate
     * @return
     */
    public static Long getPreMonths(Date weddingDate) {
        if (weddingDate == null) {
            return 99L;
        }
        Date current = new Date();
        if (current.after(weddingDate)) {
            return -1L;
        }
        Calendar d1 = new GregorianCalendar();
        d1.setTime(new Date());
        Calendar d2 = new GregorianCalendar();
        d2.setTime(weddingDate);
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        double month = Double.valueOf(days) / 30;
        System.out.println(String.format("days=%s, month=%s", days, month));
        return Long.valueOf(Math.round(month));
    }
}
