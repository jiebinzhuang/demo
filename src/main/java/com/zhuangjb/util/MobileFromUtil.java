package com.zhuangjb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 通过手机号码,获得该号码的归属地
 */
public class MobileFromUtil {
	// 正则表达式,抽取手机归属地
	public static final String REGEX_GET_MOBILE = "(?is)(<tr[^>]+>[\\s]*<td[^>]+>[\\s]*卡号归属地[\\s]*</td>[\\s]*<td[^>]+>([^<]+)</td>[\\s]*</tr>)"; // 2:from
	// 正则表达式,审核要获取手机归属地的手机是否符合格式,可以只输入手机号码前7位
	public static final String REGEX_IS_MOBILE = "(?is)(^1[3|4|5|7|8][0-9]\\d{4,8}$)";

	/**
	 * 获得手机号码归属地
	 * 
	 * @param mobileNumber
	 * @return
	 * @throws Exception
	 */
	public static String getMobileFrom(String mobileNumber) throws Exception {
		if (!veriyMobile(mobileNumber)) {
			throw new Exception("不是完整的11位手机号或者正确的手机号前七位:" + mobileNumber);
		}
		HttpClient client = null;
		GetMethod method = null;
		int httpStatusCode;

		String htmlSource = null;
		String result = null;

		try {
			client = new HttpClient();
			client.getHostConfiguration().setHost("www.ip138.com", 8080, "http");
			method = new GetMethod("/search.asp?mobile=" + mobileNumber + "&action=mobile");
			// 设置编码
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GB2312");
			method.addRequestHeader("Host", "www.ip138.com:8080");
			method.addRequestHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
			method.addRequestHeader("Referer", "http://www.ip138.com:8080/search.asp?action=mobile&mobile=");
			method.addRequestHeader("Cookie",
					"pgv_pvi=8514590720; pgv_si=s2595982336; ASPSESSIONIDCSQBAQAT=CHBGFKLIPMENPCDMAMPJIPIJ");

			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
			client.executeMethod(method);
			httpStatusCode = method.getStatusLine().getStatusCode();
			if (httpStatusCode != 200) {
				throw new Exception("网页内容获取异常！Http Status Code:" + httpStatusCode);
			}

			htmlSource = method.getResponseBodyAsString();
			if (htmlSource != null && !htmlSource.equals("")) {
				result = parseMobileFrom(htmlSource);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}

		return result;

	}

	/**
	 * 从www.ip138.com返回的结果网页内容中获取手机号码归属地,结果为：省份 城市
	 * 
	 * @param htmlSource
	 * @return
	 */
	public static String parseMobileFrom(String htmlSource) {
		// Pattern p = null;
		// Matcher m = null;
		// String result = null;
		//
		// p = Pattern.compile(REGEX_GET_MOBILE);
		// m = p.matcher(htmlSource);
		//
		// while (m.find()) {
		// if (m.start(2) > 0) {
		// result = m.group(2);
		// result = result.replaceAll("&nbsp;", " ");
		// }
		// }

		if (htmlSource == null || htmlSource.length() == 0) {
			return null;
		}
		htmlSource = htmlSource.replace("\r\n", "");
		htmlSource = htmlSource.replace("&nbsp;", ",");

		String guishudi = null;
		String t1 = "卡号归属地</TD><td align=\"center\" class=tdc2><!-- <td></td> -->";
		if (htmlSource.indexOf(t1) != -1) {
			guishudi = htmlSource.substring(htmlSource.indexOf(t1));
			guishudi = guishudi.substring(t1.length());
			guishudi = guishudi.substring(0, guishudi.indexOf("</TD>"));
		} else {
			t1 = "<!-- <td width=\"138\" align=\"center\">卡号归属地</TD> --> <TD class=\"tdc2\" align=\"center\">";
			if (htmlSource.indexOf(t1) != -1) {
				guishudi = htmlSource.substring(htmlSource.indexOf(t1));
				guishudi = guishudi.substring(t1.length());
				guishudi = guishudi.substring(0, guishudi.indexOf("</TD>"));
			} else {
				t1 = "卡号归属地</TD>		<TD width=* align=\"center\" class=tdc2>";
				if (htmlSource.indexOf(t1) != -1) {
					guishudi = htmlSource.substring(htmlSource.indexOf(t1));
					guishudi = guishudi.substring(t1.length());
					guishudi = guishudi.substring(0, guishudi.indexOf("</TD>"));
				} else {
					return null;
				}
			}
		}

		if (!guishudi.contains(",")) {
			guishudi += ",";
		}

		String kaleixing = null;
		String t2 = "卡&nbsp;类&nbsp;型</TD><TD align=\"center\" class=tdc2><!-- <td></td> -->";
		if (htmlSource.indexOf(t2) != -1) {
			kaleixing = htmlSource.substring(htmlSource.indexOf(t2));
			kaleixing = kaleixing.substring(t2.length());
			kaleixing = kaleixing.substring(0, kaleixing.indexOf("</TD>"));
		} else {
			t2 = "<TD width=\"138\" align=\"center\" noswap>卡,类,型</td><td align=\"center\" class='tdc2'>";
			if (htmlSource.indexOf(t2) != -1) {
				kaleixing = htmlSource.substring(htmlSource.indexOf(t2));
				kaleixing = kaleixing.substring(t2.length());
				kaleixing = kaleixing.substring(0, kaleixing.indexOf("</TD>"));
			} else {
				t2 = "<TD width=\"138\" align=\"center\" noswap>卡,类,型</TD><TD align=\"center\" class=tdc2><!-- <td></td> -->";
				if (htmlSource.indexOf(t2) != -1) {
					kaleixing = htmlSource.substring(htmlSource.indexOf(t2));
					kaleixing = kaleixing.substring(t2.length());
					kaleixing = kaleixing.substring(0, kaleixing.indexOf("</TD>"));
				} else {
					t2 = "卡,类,型</TD>		<TD width=* align=\"center\" class=tdc2>";
					if (htmlSource.indexOf(t2) != -1) {
						kaleixing = htmlSource.substring(htmlSource.indexOf(t2));
						kaleixing = kaleixing.substring(t2.length());
						kaleixing = kaleixing.substring(0, kaleixing.indexOf("</TD>"));
					} else {
						return null;
					}
				}
			}
		}

		guishudi += ",";
		if (kaleixing.contains("移动")) {
			guishudi += "移动,";
		} else if (kaleixing.contains("联通")) {
			guishudi += "联通,";
		} else if (kaleixing.contains("电信")) {
			guishudi += "电信,";
		} else {
			guishudi += "未知,";
		}
		guishudi += kaleixing;

		return guishudi;

	}

	/**
	 * 验证手机号
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public static boolean veriyMobile(String mobileNumber) {
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile(REGEX_IS_MOBILE);
		m = p.matcher(mobileNumber);

		return m.matches();
	}

	public static void main(String[] args) throws Exception {
		// 福建,厦门市,移动,移动全球通卡
		System.out.println(getMobileFrom("13400688403"));
		System.out.println(getMobileFrom("13400688403"));
		System.out.println(getMobileFrom("13400688403"));
		System.out.println(getMobileFrom("13400688403"));
		System.out.println(getMobileFrom("13400688403"));
	}

}