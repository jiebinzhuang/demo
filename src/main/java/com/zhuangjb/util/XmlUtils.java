package com.zhuangjb.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XmlUtils {
	// private static final Log logger = LogFactory.getLog(XmlUtils.class);

	public static void outputXml(OutputStream os, Document xml, String encoding, boolean formatFlag) throws Exception {
		OutputFormat format = null;
		if (formatFlag) {
			format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding);
		} else {
			format = OutputFormat.createCompactFormat();
			format.setEncoding(encoding);
		}
		XMLWriter output = new XMLWriter(os, format);
		output.write(xml);
		output.close();
	}

	public static String outputXml(Document xml, String encoding, boolean formatFlag) throws IOException {
		OutputFormat format = null;
		if (formatFlag) {
			format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding);
		} else {
			format = OutputFormat.createCompactFormat();
			format.setEncoding(encoding);
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		XMLWriter output = new XMLWriter(bos, format);
		output.write(xml);
		output.close();

		return new String(bos.toByteArray(), encoding);
	}

	public static void outputXml(String filePathName, Document document, String encoding) throws Exception {
		outputXml(filePathName, document, encoding, false);
	}

	public static void outputXml(String filePathName, Document document, String encoding, boolean formatFlag)
			throws Exception {
		if (document == null) {
			return;
		}
		OutputFormat format = null;
		// 对于xml输出格式化暂时不用考虑
		if (formatFlag) {
			format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding);
		} else {
			format = OutputFormat.createCompactFormat();
			format.setEncoding(encoding);
		}
		FileOutputStream fos = new FileOutputStream(filePathName);
		XMLWriter output = new XMLWriter(fos, format);
		// 如果document为null,则创建一个空的xml
		output.write(document);
		output.close();
		fos.close();
	}

	public static Document parseText(String text) throws DocumentException {
		return DocumentHelper.parseText(text);
	}
}
