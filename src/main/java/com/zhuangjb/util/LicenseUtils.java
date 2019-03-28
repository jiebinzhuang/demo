package com.zhuangjb.util;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class LicenseUtils {
	// private static Properties configProps = null;

	public static String getConfig(String key) {
		return getConfigProperties().getProperty(key);
	}

	public static String getConfig(String key, String defaultValue) {
		return getConfigProperties().getProperty(key, defaultValue);
	}

	public static Properties getConfigProperties() {
		// if (configProps == null) {
		Properties configProps = new Properties();
		try {
			InputStream is = LicenseUtils.class.getResourceAsStream("/license.properties");
			configProps.load(is);
		} catch (Exception e) {
			throw new RuntimeException("license error!", e);
		}
		// }
		return configProps;
	}

	public static boolean checkLicense() {
		try {
			String name = getConfig("name");
			String expiry = getConfig("expiry");
			String signature = getConfig("signature");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(expiry);
			if (d.before(new Date())) {
				return false;
			}

			String md5 = new MD5().encrypt(name + expiry + "www.zhuangjb.name1234567890");

			if (md5.equals(signature)) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
		}
		return false;
	}

}
