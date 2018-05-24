package com.shared.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DataHelper {
	
	public static Map<String, Boolean> checkbox;
	
	static {
		checkbox = new HashMap<String, Boolean>();
		checkbox.put("1", true);
		checkbox.put("0", false);
		checkbox.put("", false);
	}
	
	public static int parseInt(String value) {
		if (StringUtils.isNumeric(value)) {
			return (value != null && ! value.isEmpty()) ? Integer.parseInt(value) : 0;
		} else {
			return 0;
		}
	}

}
