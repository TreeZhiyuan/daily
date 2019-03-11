package com.example.daily.apache.http;

import com.example.daily.redis.User;
import com.gexin.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhiyuan
 * @date: 2017年11月15日
 * @project: spring-boot-demo
 * @description:
 */
@Slf4j
public class DaliyTest {
    @Test
    public void testListAdd() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setEmail("123" + String.valueOf(i) + "@l61.com");
            users.add(user);
        }
        System.out.println(users.size());
    }

    @Test
    public void testPwdFormate(){
        Object a =null;
        System.out.println(a+"");

        boolean result = true;
        result = Boolean.valueOf("null");
        result = Boolean.valueOf("");
        result = Boolean.valueOf(null);
        result = Boolean.valueOf("hello thank u");
        result = Boolean.valueOf("True");
    }

    @Test
    public void test() {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < 93; i++) {
            ids.add(Long.valueOf(i + 1));
        }

        int batchCount = 50;
        int startIndex = 0; // 从第0个下标开始
        while (startIndex < ids.size()) {
            int endIndex = 0;
            if (ids.size() - batchCount < startIndex) {
                endIndex = ids.size();
            } else {
                endIndex = startIndex + batchCount;
            }
            List subUserIds = ids.subList(startIndex, endIndex);

            System.out.print(subUserIds);
            System.out.print("---------------------------------");
            startIndex = startIndex + batchCount;
        }


    }

    public static<T> List<List<T>> batchList(List<T> sourceList, int batchCount) {
        List<List<T>> returnList = new ArrayList<>();
        int startIndex = 0; // 从第0个下标开始
        while (startIndex < sourceList.size()) {
            int endIndex = 0;
            if (sourceList.size() - batchCount < startIndex) {
                endIndex = sourceList.size();
            } else {
                endIndex = startIndex + batchCount;
            }
            returnList.add(sourceList.subList(startIndex, endIndex));
            startIndex = startIndex + batchCount; // 下一批
        }
        return returnList;
    }

    @Test
    public void test222() {
        System.out.print(Long.valueOf(null));
        String content = "语文:${语文},dwadw:${语文}";
        String jsonMessage = "{\"语文\":\"88\"}";
        Map<String, Object> object = JSONObject.parseObject(jsonMessage);
        for (String key : object.keySet()) {
            String value = object.get(key).toString();
            content = content.replace(String.format("${%s}", key), value);
        }
        System.out.println(content);
    }

    @Test
    public void test2() {
        boolean t = Boolean.valueOf("daw");

        final String url = "http://www.baidu.com?q=微信&filter=%s";
        String encodedUrl = "";
        try {
            encodedUrl = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.info("UnsupportedEncodingException for url: {},charset:{}", url, "UTF-8");
        } finally {
        }
        System.out.println(encodedUrl);
        String decodedUrl = null;
        try {
            decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.info("UnsupportedEncodingException for url: {},charset:{}", encodedUrl, "UTF-8");
            e.printStackTrace();
        } finally {
        }
        System.out.println(decodedUrl);
    }

    @Test
    public void testReplace() {
        String detailUrl = "ciw://mall/merchant?store_id={storeId},this {storeId} should be replaced";
        detailUrl = detailUrl.replace("{storeId}", "12093");
        detailUrl = "ciw://mall/merchant?store_id=%d,this storeId should be replaced";
        System.out.println(detailUrl);
        detailUrl = String.format(detailUrl, 12093);
        System.out.println(detailUrl);
    }

    public static void main(String[] args) throws IOException {
        String buyMoney = "0.90";
        BigDecimal payMoney = (BigDecimal) null;
        BigDecimal buyMoney2 = new BigDecimal(buyMoney);
        System.out.println(payMoney + "<<>>" + buyMoney2);
        Pattern p = Pattern.compile(
                "(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d) (\\d\\d):(\\d\\d):(\\d\\d)");
        String rightNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date());
        System.out.println("myString: " + rightNow);
        Matcher m = p.matcher(rightNow);
        while (m.find()) {
            System.out.printf(
                    "origin string: %s, find the year: %s, the month: %s, "
                            + "the date: %s, the hour: %s, the minute: %s, "
                            + "the second: %s\n\r",
                    m.group(0), m.group(1), m.group(2), m.group(3), m.group(4),
                    m.group(5), m.group(6));
        }

        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        // true 因为JVM会擦除所有泛型类型标记
        System.out.println(c1 == c2);

        String code = "{\"type\":\"10\",\"token\":\"9538288D8F4C3757F6969E88E40A3E75\"}";
        String encoded = java.net.URLEncoder.encode(code, "UTF-8");
        System.out.printf("encoded result: %s\r\n", encoded);
        String decoded = java.net.URLDecoder.decode(encoded, "UTF-8");
        System.out.printf("decoded result: %s\r\n", decoded);
        System.out.println("end");
        /**
         * return value of the method for hashmap put
         */
        HashMap<String, String> map = new HashMap<String, String>();
        System.out.println(map.containsKey("1"));
        map.put("1", null);
        System.out.println(map.put("1", "11"));
        System.out.println(map.containsKey("2"));
        System.out.println(map.put("2", "11"));
        List<String> urls = new ArrayList<String>() {
            private static final long serialVersionUID = 1L;

            {
                add("/role/listRole.do");
                add("/account/getAccountList.do");
                add("/menu/*");
                add("/miner/*");
                add("/order/getOrderList.do");
                add("/expressConpany/getExpressConpanyList.do");
                add("/trusteeshipOrder/getTrusteeshipOrderList.do");
                add("/excel/order.do");
                add("/banner/getBanners.do");
                add("/tutorial/getTutorialList.do");
                add("/tutorial/getTutorialList.do");
                add("/account/getAccountList.do");
                add("/agreement/getAgreementList.do");
                add("/account/addOrUpdateAccount.do");
            }
        };
        String requestUri = "/miner/add.do";
        String[] sus = requestUri.split("/");
        System.out.println("///////////////" + (urls.contains(requestUri)
                || urls.contains("/" + sus[1] + "/*")));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(today);
        now.add(Calendar.MONTH, -3);
        System.out.println(sdf.format(today) + "_____________"
                + sdf.format(now.getTime()));

    }
}
