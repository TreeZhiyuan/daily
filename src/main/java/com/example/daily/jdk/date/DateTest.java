package com.example.daily.jdk.date;

import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: zhiyuan
 * @date: 2018-07-10
 * @project: daily
 * @description:
 */

public class DateTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        List<String> ls = new ArrayList<String>() {{
            add("1");
            add("1l");
            add("1lll");
            add("1l@ll");
        }};

        System.out.printf("%s\r\n", ls.subList(0, 3));

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.set(2000, 1, 20);
        c.add(Calendar.DAY_OF_MONTH, 10);
        System.out.println(sf.format(c.getTime()));
    }

    private boolean isValidDate(String date, String template) {
        boolean convertSuccess = true;
        // 指定日期格式
        SimpleDateFormat format = new SimpleDateFormat(template, Locale.CHINA); // 例如：yyyy-MM-dd
        // HH:mm:ss
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(date);
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    @Test
    public void testCalendar() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        // 得到当前时间
        Calendar cal = Calendar.getInstance();
        // 转换成格林威治时间
        cal.setTimeInMillis(0);
        System.out.println("-----------------" + format.format(cal.getTime()));

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        System.out
                .println(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);

        System.out.println(format.format(calendar.getTime()));
    }

    @Test
    public void testClearHMSCalendar() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, 3);
        // now.clear(Calendar.HOUR); or now.clear(Calendar.HOUR_OF_DAY); does not work well
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.clear(Calendar.MINUTE);
        now.clear(Calendar.SECOND);
        now.clear(Calendar.MILLISECOND);
        System.out.println(format.format(now.getTime()));

        now.set(9998, 11, 31);
        System.out.println(format.format(now.getTime()));

    }

    @Test
    public void checkFaultTolerance() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 1, 30);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);

        Date time = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format = df.format(time);
        System.out.println(format);
    }

    @Test
    public void testCalendarSetUp() {
        Calendar c = Calendar.getInstance();  // 当前时间的日历对象。
        c.set(2018, 2, 28);
        System.out.println(c.get(Calendar.MONTH));
        System.out.println(c.get(Calendar.YEAR));

        Calendar c2 = Calendar.getInstance();  // 当前时间的日历对象。
        c2.set(2018, 2, 30);
        //删除下面两行与不删除下面两行  debug查看的结果截然不同
        System.out.println(c2.get(Calendar.MONTH));
        System.out.println(c2.get(Calendar.YEAR));
        System.out.println(c.after(c2)); // c在c2之后或者相等的话 return false
        System.out.println(c.before(c2));// c在c2之前或者相等的话 return true
    }

    @Test
    public void test222() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, 4);
        c.set(Calendar.DATE, 13);
        System.out.println(c.get(Calendar.MONTH));
        System.out.println(c.get(Calendar.YEAR));
        System.out.println(c.get(Calendar.DATE));
    }


    @Test
    public void checkC() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 0);
        now.clear(Calendar.MINUTE);
        now.clear(Calendar.SECOND);

        System.out.println(format.format(now.getTime()));
        now.add(Calendar.DATE, 4);
        System.out.println(format.format(now.getTime()));
    }

    @Test
    public void testCalendarRegion() {
        Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+1"));
        System.out.println("Millis = " + calendar1.getTimeInMillis());
        System.out.println("Millis = " + calendar2.getTimeInMillis());
        System.out.println("hour = " + calendar1.get(Calendar.HOUR));
        System.out.println("hour = " + calendar2.get(Calendar.HOUR));
        System.out.println("date = " + calendar1.getTime());
        System.out.println("date = " + calendar2.getTime());
    }

    /**
     * @param d1
     * @param d2
     * @return
     */
    public int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    @Test
    public void testCutDown() {
        Calendar cal = Calendar.getInstance();
        cal.set(2019, 1, 26);
        Date currentDate = new Date();
        System.out.println(cal.getTime().getTime());
        System.out.println(currentDate.getTime());
    }

    @Test
    public void TestBetweenDays() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2000, 1, 20);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2000, 1, 22);
        int days = getDaysBetween(cal1, cal2);
        System.out.println(days);
    }

}
