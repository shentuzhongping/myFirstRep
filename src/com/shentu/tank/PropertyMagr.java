package com.shentu.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMagr {
	
	static Properties prop = new Properties();
	
	static {
		try {
			prop.load(PropertyMagr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		if (prop == null) return null;
		return prop.getProperty(key);
	}

}
