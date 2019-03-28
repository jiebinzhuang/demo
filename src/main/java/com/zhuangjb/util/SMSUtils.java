package com.zhuangjb.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SMSUtils {
	private static final Logger log = Logger.getLogger(SMSUtils.class);
	private static Properties prop = null;

	public static Properties getProperties() {
		if (prop == null) {
			prop = new Properties();
			try {
				InputStream is = SMSUtils.class.getResourceAsStream("/sms.properties");
				prop.load(is);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new RuntimeException("sms error!", e);
			}
		}
		return prop;
	}
}
