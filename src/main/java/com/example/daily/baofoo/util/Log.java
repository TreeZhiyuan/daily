/**
 * @author Administrator
 *
 */
package com.example.daily.baofoo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	
	public static void  Write(String msg) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + "\t: " + msg);
	}
	
}