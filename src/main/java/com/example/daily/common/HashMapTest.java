package com.example.daily.common;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author: zhiyuan
 * @date: 2018-03-02
 * @project: spring-boot-demo
 * @description: hashmap遍历性能比较
 */

public class HashMapTest {
	public static void main(String[] args) {
		HashMap hashmap = new HashMap();
		for (int i = 0; i < 1000; i++) {
			hashmap.put("k_" + i, "speed_" + i);
		}
		keySetTest(hashmap);
		entrySetTest(hashmap);
		forEntrySet(hashmap);

		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("k1", "v1");
		paraMap.put("k2", "v2");

	}

	public static void keySetTest(HashMap hashmap) {
		long begin = Calendar.getInstance().getTimeInMillis();
		Iterator iterator = hashmap.keySet().iterator();
		while (iterator.hasNext()) {
			System.out.print(hashmap.get(iterator.next()));
		}
		System.out.println("\n\rkeySet Time");
		System.out.println(Calendar.getInstance().getTimeInMillis() - begin);
	}

	public static void forEntrySet(HashMap<String, String> hashmap) {
		long begin = Calendar.getInstance().getTimeInMillis();
		for (Entry<?, ?> entry : hashmap.entrySet()) {
			System.out.print("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
		System.out.println("\n\rFor Loop Time:");
		System.out.println(Calendar.getInstance().getTimeInMillis() - begin);
	}

	public static void entrySetTest(HashMap hashmap) {
		long begin = Calendar.getInstance().getTimeInMillis();
		Iterator it = hashmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			System.out.print(entry.getValue());
		}
		System.out.println("\n\rEntry Time:");
		System.out.println(Calendar.getInstance().getTimeInMillis() - begin);
	}

}