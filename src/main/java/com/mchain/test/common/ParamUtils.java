package com.mchain.test.common;

import java.util.Map;

public class ParamUtils {
	
	public static String getString(Map<?,?> map, String key) {
		if(map == null) return null;
		if(map.get(key) != null) return map.get(key).toString();
		return null;
	}
	
	public static Integer getInteger(Map<?,?> map, String key) {
		if(map == null) return null;
		try {
			if(map.get(key) != null) return new Integer(map.get(key).toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

