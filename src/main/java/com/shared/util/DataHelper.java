package com.shared.util;

import java.util.HashMap;
import java.util.Map;

public class DataHelper {
	
	public static Map<String, Boolean> checkbox;
	
	static {
		checkbox = new HashMap<String, Boolean>();
		checkbox.put("1", true);
		checkbox.put("", false);
	}
	
	public static int parseInt(String value) {
		return (value != null && ! value.isEmpty()) ? Integer.parseInt(value) : 0;
	}

}
