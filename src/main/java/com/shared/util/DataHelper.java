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

}