package com.zhuangjb.busy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			String pk10url = "http://www.bwlc.net/bulletin/trax.html";// http://88721.988cc.cc/member/Game/getGameLottery_js

			HttpClient client = new HttpClient();
			GetMethod postMethod = new GetMethod(pk10url);
			try {
				postMethod.addRequestHeader("Accept",
						"text/html, application/xhtml+xml,image/jxr, */*");
				postMethod.addRequestHeader("Accept-Language", "zh-CN");
				postMethod
						.addRequestHeader("User-Agent",
								"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; Touch; rv:11.0) like Gecko");
				postMethod.addRequestHeader("Accept-Encoding", "");
				postMethod.addRequestHeader("Connection", "Keep-Alive");
				postMethod.addRequestHeader("Host", "www.bwlc.net");
			 

//				postMethod.addRequestHeader("Cookie", "HttpOnly=true; UM_distinctid=15c7197ebf69c-0c161a972a741d-7a157270-1fa400-15c7197ebf7375; JSESSIONID=node1mfqyhzcxu6jdowbwpeqm7g65.node1; CNZZDATA1260158960=1945224088-1496549598-null%7C1496755887; Hm_lvt_d1cdc5cd5c769566cc0031ce552afe4e=1496554401,1496758396; Hm_lpvt_d1cdc5cd5c769566cc0031ce552afe4e=1496758396; _gscu_1916621285=96554401gt4nm890; _gscs_1916621285=96758396yw3jl616|pv:1; _gscbrs_1916621285=1; Hm_lvt_59f2f67e65fdaf7d9a2055f2c512fbc0=1496554401,1496758397; Hm_lpvt_59f2f67e65fdaf7d9a2055f2c512fbc0=1496758397");

				client.getHttpConnectionManager().getParams().setSoTimeout(10000);
				client.executeMethod(postMethod);

				String html = postMethod.getResponseBodyAsString();// .getResponseBodyAsString();
 
				 System.out.print(html);
			} catch (Exception e) {
				 
//				throw new Exception("异常：", e);
			} finally {
				postMethod.releaseConnection();
				client.getHttpConnectionManager().closeIdleConnections(0);
			}
	}

}
