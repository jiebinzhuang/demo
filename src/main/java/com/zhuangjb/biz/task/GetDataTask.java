package com.zhuangjb.biz.task;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.zhuangjb.busy.BusyServer;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.util.DateUtils;
import com.zhuangjb.util.SystemUtils;
import com.mongodb.BasicDBObject;

/**
 * 定时统计报表信息
 * 
 * @author test
 *
 */
public class GetDataTask implements Runnable {

	protected Log log = LogFactory.getLog(this.getClass());

	@Override
	public void run() {

		while (!BusyServer.getInstance().isStopThreadSignal()) {
			log.info("processor start:" + this.getClass().toString());
			SystemUtils.delayTimer(2000);

			String pk10url = "http://www.bwlc.net/bulletin/trax.html";// http://88721.988cc.cc/member/Game/getGameLottery_js

			log.info(this.getClass().getName() + "getOpenResult URL" + pk10url);
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

				client.getHttpConnectionManager().getParams()
						.setSoTimeout(10000);
				client.executeMethod(postMethod);

				String html = postMethod.getResponseBodyAsString();// .getResponseBodyAsString();

				html = html.substring(html.indexOf("	"));
				html = html.substring(html.indexOf("<td>") + 4);
				String qishu = html.substring(0, 6);
				html = html.substring(html.indexOf("<td>") + 4);
				String results = html.substring(0, html.indexOf("</td>"));

				html = html.substring(html.indexOf("<td>") + 4);

				String opentime = html.substring(0, html.indexOf("</td>"));

				System.out.println(qishu);
				System.out.println(results);

				log.error("openttime" + opentime);

				String args[] = new String[12];
				args[0] = qishu;
				args[11] = opentime;
				String[] resultarry = results.split(",");

				boolean isok = true;
				for (int i = 0; i < 10; i++) {

					String po = resultarry[i].substring(1, 2);
					if ("0".equals(po)) {
						po = "10";
					}
					System.out.println("任务1采集"+i + "-------------" + po);
					if (Integer.valueOf(po) < 11 && Integer.valueOf(po) > 0) {
						args[i + 1] = po;
					} else {
						log.error("---------------------------------------------------严重错误------------------------------------");
						isok = false;
					}

				}
				if (isok) {
					log.error("ok1");

					BasicDBObject qryfilter2 = new BasicDBObject();
					qryfilter2.put(DBC.dr, 0);
					qryfilter2.append("qishu", args[0]);

					Document qrydoc2 = MongoDAO.getInstance().findOne(
							"pk10_openresult", qryfilter2);
					if (qrydoc2 != null) {

						String status = qrydoc2.getString("status");
						if (!"Y".equals(status)) {

							String position1 = qrydoc2.getString("position1");
							String position2 = qrydoc2.getString("position2");
							String position3 = qrydoc2.getString("position3");
							String position4 = qrydoc2.getString("position4");
							String position5 = qrydoc2.getString("position5");
							String position6 = qrydoc2.getString("position6");
							String position7 = qrydoc2.getString("position7");
							String position8 = qrydoc2.getString("position8");
							String position9 = qrydoc2.getString("position9");
							String position10 = qrydoc2.getString("position10");
							if (position1.equals(args[1])
									&& position2.equals(args[2])
									&& position3.equals(args[3])
									&& position4.equals(args[4])
									&& position5.equals(args[5])
									&& position6.equals(args[6])
									&& position7.equals(args[7])
									&& position8.equals(args[8])
									&& position9.equals(args[9])
									&& position10.equals(args[10])) {
								qrydoc2.put("status", "Y");
								MongoDAO.getInstance().findOneAndUpdate(
										"pk10_openresult", qryfilter2,
										new BasicDBObject("$set", qrydoc2));
								log.info("更新开奖结果状态为正常");
							} else {
								qrydoc2.put("status", "N");
								qrydoc2.put("position1", args[1]);
								qrydoc2.put("position2", args[2]);
								qrydoc2.put("position3", args[3]);
								qrydoc2.put("position4", args[4]);
								qrydoc2.put("position5", args[5]);
								qrydoc2.put("position6", args[6]);
								qrydoc2.put("position7", args[7]);
								qrydoc2.put("position8", args[8]);
								qrydoc2.put("position9", args[9]);
								qrydoc2.put("position10", args[10]);

								MongoDAO.getInstance().findOneAndUpdate(
										"pk10_openresult", qryfilter2,
										new BasicDBObject("$set", qrydoc2));
								log.info("更新开奖结果状态为异常");
							}
						}
					} else {
						Document doc = new Document();
						doc.put("qishu", args[0]);
						doc.put("position1", args[1]);
						doc.put("position2", args[2]);
						doc.put("position3", args[3]);
						doc.put("position4", args[4]);
						doc.put("position5", args[5]);
						doc.put("position6", args[6]);
						doc.put("position7", args[7]);
						doc.put("position8", args[8]);
						doc.put("position9", args[9]);
						doc.put("position10", args[10]);

						doc.put("opentime", args[11]);
						String openmin = args[11].split(":")[1];
						log.error("openmin" + openmin);// 2017-06-11 23:57
						doc.put("openmin", openmin);
						doc.put("opendate", DateUtils.getCurrentDate());

						doc.put(DBC.ts, DateUtils.getTS());
						doc.put(DBC.dr, 0);

						// 新增
						doc.put(DBC.id, new ObjectId());

						MongoDAO.getInstance().insert("pk10_openresult", doc);
					}
				}
			} catch (Exception e) {
				log.error("异常：", e);
			} finally {
				postMethod.releaseConnection();
				client.getHttpConnectionManager().closeIdleConnections(0);
			}

		}
		/**
		 * 线程退出
		 */
		log.info("processor end:" + this.getClass().toString());
	}

}
