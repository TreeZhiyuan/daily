package com.example.daily.apache.http;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhiyuan
 * @date: 2017年11月15日
 * @project: spring-boot-demo
 * @description:
 */
public class DaliyTest {

	public static void main(String[] args) throws IOException {
		Pattern p = Pattern.compile("(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d) (\\d\\d):(\\d\\d):(\\d\\d)");
		String rightNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println("myString: " + rightNow);
		Matcher m = p.matcher(rightNow);
		while (m.find()) {
			System.out.printf(
					"find the year: %s, the month: %s, the date: %s, the hour: %s, the minute: %s, the second: %s\n\r",
					m.group(1), m.group(2), m.group(3), m.group(4), m.group(5), m.group(6));
		}

		Class c1 = new ArrayList<String>().getClass();
		Class c2 = new ArrayList<Integer>().getClass();
		// true 因为JVM会擦除所有泛型类型标记
		System.out.println(c1 == c2);

		String code = "{\"type\":\"10\",\"token\":\"9538288D8F4C3757F6969E88E40A3E75\"}";
		String encoded = java.net.URLEncoder.encode(code, "UTF-8");
		System.out.printf("encoded result: %s\n", encoded);
		String decoded = java.net.URLDecoder.decode(encoded, "UTF-8");
		System.out.printf("decoded result: %s\r", decoded);
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
		List urls = new ArrayList<String>() {
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
		System.out.println("///////////////" + (urls.contains(requestUri) || urls.contains("/" + sus[1]+"/*")));
	}
}
