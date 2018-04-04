package com.example.daily.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class SystemProperty {
	private static Map<String,String> httpCfgParamMap = null;
	public static String getProperty(String propertyName) {
		if(null == httpCfgParamMap){
			init();
		}
		return httpCfgParamMap.get(propertyName);
	}
	
	public static String getProperty(String propertyName, String defaultVal) {
		String value = getProperty(propertyName);
		if(StringUtils.isEmpty(value)){
			return defaultVal;
		}
		return value;
	}
	
	private static void init(){
		InputStream in =   SystemProperty.class.getClassLoader().getResourceAsStream("application.properties");
		InputStreamReader sr = null;
		try {
			sr = new InputStreamReader(in, "UTF-8");
			Properties prop = new Properties();
			prop.load(sr);
			Set set = prop.stringPropertyNames();
			Iterator<String> it = set.iterator();
			httpCfgParamMap = new HashMap();
			while(it.hasNext()){
				String key = it.next();
				httpCfgParamMap.put(key, prop.getProperty(key).trim());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
