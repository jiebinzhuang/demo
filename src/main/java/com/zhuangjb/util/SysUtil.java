package com.zhuangjb.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

public class SysUtil {
	public static final String EMPTY_STRING = "";
	public static final String SHORTURL_SERVER = "http://raiyi.cn/shorturlapi?oriurl=";

	public static String parseFlowData(String dataStr, String field) {
		String ret = "";
		try {
			ret = dataStr.substring(dataStr.indexOf(field));
			if (ret != null && ret.indexOf(",") != -1) {
				ret = ret.substring(field.length(), ret.indexOf(","));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String formatValue(int i) {
		return String.format("%1$,02d", i);
	}

	public static String getDecimalFormatValue(double p) {
		DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();
		nf.applyPattern("00.00%"); // 00表示小数点2位
		nf.setMaximumFractionDigits(2); // 2表示精确到小数点后2位
		return nf.format(p);
	}

	public static String defaultString(String str) {
		return isEmpty(str) ? "default" : str;
	}

	public static boolean isEmpty(String[] array) {
		for (String str : array) {
			if (isEmpty(str))
				return true;
		}
		return false;
	}

	/**
	 * 是否为空
	 *
	 * @param str
	 * @return
	 * @author 魏柱
	 * @date 2013-3-28 下午4:30:02
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 截取字符串
	 *
	 * @return
	 * @author stone
	 * @time 2012-11-13下午10:23:46
	 */
	public static String substring(String str, int length, String s) {
		if (str == null || str.isEmpty() || str.length() < length) {
			return str;
		}
		str = str.substring(0, length - 1);
		return str + s;
	}

	/**
	 * 生成随机字符串
	 *
	 * @return String
	 */
	public static String UUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		str = str.replaceAll("-", "");
		return str;
	}

	/**
	 * 对原始字符串进行编码转换，如果失败，返回原始的字符串
	 *
	 * @param s
	 *            原始字符串
	 * @param srcEncoding
	 *            源编码方式
	 * @param destEncoding
	 *            目标编码方式
	 * @return 转换编码后的字符串，失败返回原始字符串
	 */
	public static String getString(String s, String srcEncoding, String destEncoding) {
		try {
			return new String(s.getBytes(srcEncoding), destEncoding);
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 *
	 * @param b
	 *            字节数组
	 * @param encoding
	 *            编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, String encoding) {
		try {
			return new String(b, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b);
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 *
	 * @param b
	 *            字节数组
	 * @param offset
	 *            要转换的起始位置
	 * @param len
	 *            要转换的长度
	 * @param encoding
	 *            编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, int offset, int len, String encoding) {
		try {
			return new String(b, offset, len, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b, offset, len);
		}
	}

	/**
	 * 截取字符串，包含中文
	 *
	 * @return
	 */
	public final static String CutTrueLength(String strContent, int maxTrueLength) {
		String strNew = strContent;

		if (strContent == null || maxTrueLength <= 0) {
			strNew = "";

			return strNew;
		}

		int trueLen = TrueLength(strContent);

		if (trueLen > maxTrueLength) {
			for (int i = strContent.length() - 1; i > 0; i--) {
				strNew = strNew.substring(0, i);

				if (TrueLength(strNew) == maxTrueLength) {
					break;
				} else if (TrueLength(strNew) < maxTrueLength) {
					strNew += "";
					break;
				}
			}
		}

		return strNew + "...";
	}

	/**
	 * 字符串长度，包含中文
	 *
	 * @param strContent
	 * @return
	 */
	public final static int TrueLength(String strContent) {
		int lengthTotal = 0;// 长度总计

		int n = strContent.length();

		String strWord = "";

		int asc;

		for (int i = 0; i < n; i++) {
			strWord = strContent.substring(i, i + 1);
			asc = strWord.charAt(0);
			if (asc < 0 || asc > 127)
				lengthTotal = lengthTotal + 2;
			else
				lengthTotal = lengthTotal + 1;
		}

		return lengthTotal;
	}

	public static String abbreviate(String str, int width, String ellipsis) {
		if (str == null || "".equals(str)) {
			return "";
		}

		int d = 0; // byte length
		int n = 0; // char length
		for (; n < str.length(); n++) {
			d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;
			if (d > width) {
				break;
			}
		}

		if (d > width) {
			n = n - ellipsis.length() / 2;
			return str.substring(0, n > 0 ? n : 0) + ellipsis;
		}

		return str = str.substring(0, n);
	}

	/**
	 * @return String Object
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	@SuppressWarnings("rawtypes")
	public static boolean isSetEqual(Set set1, Set set2) {

		if (set1.size() != set2.size()) {
			return false;
		}

		Iterator ite1 = set1.iterator();
		Iterator ite2 = set2.iterator();

		boolean isFullEqual = true;
		while (ite1.hasNext()) {
			if (!set2.contains(ite2.next())) {
				isFullEqual = false;
			}
		}

		return isFullEqual;
	}

	/**
	 * 深度克隆对象
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deepClone(T t) throws Exception {

		// 将对象写到流里
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(t);

		// 从流里读出来
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (T) (oi.readObject());
	}

	/**
	 * 把Map所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 *
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String preStr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				preStr = preStr + key + "=" + value;
			} else {
				preStr = preStr + key + "=" + value + "&";
			}
		}

		return preStr;
	}

	public static String toUtf8String(String str) {
		try {
			if (str != null)
				return new String(str.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String objectToString(Object obj) {
		if (obj == null) {
			return EMPTY_STRING;
		}
		return truncValue(obj.toString());
	}

	public static String truncValue(String str) {
		if (isEmpty(str)) {
			return EMPTY_STRING;
		}
		return str;
	}

	public static String retainsArray(String a, String b) {
		StringBuffer al = new StringBuffer();
		for (String at : a.split(",")) {
			for (String bt : b.split(",")) {
				if (at.equals(bt)) {
					al.append(at + ",");
					break;
				}
			}
		}
		if (al.toString().length() > 0)
			al.setLength(al.length() - 1);
		return al.toString();
	}

	public static String convertString(String src, String APISrcCharSet, String APIDestCharSet) {
		String ret = src;
		if (src != null) {
			try {
				ret = new String(src.getBytes(APISrcCharSet), APIDestCharSet);
			} catch (UnsupportedEncodingException e) {
				ret = src;
			}
		}
		return ret;
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	@SuppressWarnings("unused")
	public static List<String> readFileByLines(String fileName) {
		List<String> lineList = new ArrayList<String>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				lineList.add(tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return lineList;
	}

	/**
	 * 将InputStream转换成String
	 * 
	 * @param in
	 *            InputStream
	 * @return String
	 * @throws Exception
	 *
	 */
	public static String InputStreamTOString(InputStream in) throws Exception {
		int BUFFER_SIZE = 4096;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return new String(outStream.toByteArray(), "UTF-8");
	}

	/**
	 * 21. * 使用java正则表达式去掉多余的.与0 22. * @param s 23. * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

}