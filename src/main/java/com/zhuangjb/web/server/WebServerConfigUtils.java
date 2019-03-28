package com.zhuangjb.web.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class WebServerConfigUtils {
	private static final Logger logger = Logger.getLogger(WebServerConfigUtils.class);
	private static String filePath = "/webserver.xml";
	private static String encoding = "UTF-8";
	private static WebServerConfigVO webServerConfig = null;
	private static List<String[]> realms = null;

	public static List<String[]> getRealms() throws IOException {
		if (realms == null) {
			// 读取
			realms = new ArrayList<String[]>();

			InputStream inputStream = WebServerConfigUtils.class.getResourceAsStream("/realm.properties");
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			while (br.ready()) {
				String str = br.readLine();
				if (str != null && str.trim().length() > 0 && !str.trim().startsWith("#")) {
					realms.add(str.trim().split("\\,"));
				}
			}
			br.close();
			inputStream.close();
		}
		return realms;
	}

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
			InputStream inputStream = WebServerConfigUtils.class.getResourceAsStream(filePath);
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

	public static WebServerConfigVO getWebServerConfig() {
		if (webServerConfig == null) {
			try {
				Document doc = getDocument();
				webServerConfig = new WebServerConfigVO();

				String hostname = doc.selectSingleNode("/config/hostname").getText();
				webServerConfig.setHostname(hostname);

				String port = doc.selectSingleNode("/config/port").getText();
				webServerConfig.setPort(Integer.parseInt(port));

				// List<?> list = doc.selectNodes("/config/users/user");
				// List<UserVO> users = new ArrayList<UserVO>();
				// for (int i = 0; i < list.size(); i++) {
				// Node node = (Node) list.get(i);
				// if (node instanceof Element) {
				// Element e = (Element) node;
				// UserVO user = new UserVO();
				// user.setName(e.attributeValue("name"));
				// user.setPassword(e.attributeValue(DBC.password));
				// users.add(user);
				// }
				// }
				// adminConfigVO.setUsers(users);
			} catch (Exception e) {
				logger.error("配置文件读取失败!", e);
				throw new RuntimeException("配置文件读取失败!", e);
			}
		}
		return webServerConfig;
	}

}
