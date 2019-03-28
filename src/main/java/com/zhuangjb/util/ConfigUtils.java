package com.zhuangjb.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class ConfigUtils {
	private static final Logger logger = Logger.getLogger(ConfigUtils.class);
	private static String filePath = "/system.xml";
	private static String encoding = "UTF-8";
	private static ConfigVO config = null;

	private static SAXReader getSAXReaderInstance() {
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding(encoding);
		saxReader.setIgnoreComments(false);
		return saxReader;
	}

	private static Document getDocument() {
		Document doc = null;
		Reader reader = null;
		try {
			InputStream inputStream = ConfigUtils.class.getResourceAsStream(filePath);
			if (inputStream == null) {
				throw new RuntimeException(filePath + "路径无效!");
			}

			reader = new InputStreamReader(inputStream, encoding);
			SAXReader saxReader = getSAXReaderInstance();
			doc = saxReader.read(reader);
		} catch (Exception e) {
			throw new RuntimeException(filePath, e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return doc;
	}

	public static ConfigVO getConfig() {
		if (config == null) {
			try {
				Document doc = getDocument();
				config = new ConfigVO();

				String title = doc.selectSingleNode("/system/title").getText();
				config.setTitle(title);

				String height = doc.selectSingleNode("/system/height").getText();
				config.setHeight(Integer.parseInt(height));

				String width = doc.selectSingleNode("/system/width").getText();
				config.setWidth(Integer.parseInt(width));
				
				String autoXiazhuTask = doc.selectSingleNode("/system/autoXiazhuTask").getText();
				config.setAutoXiazhuTask(Integer.parseInt(autoXiazhuTask));
				
			 
				
			} catch (Exception e) {
				logger.error("配置文件读取失败!", e);
				throw new RuntimeException("配置文件读取失败!", e);
			}
		}
		return config;
	}

}
