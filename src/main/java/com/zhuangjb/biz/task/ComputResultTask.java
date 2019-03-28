package com.zhuangjb.biz.task;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;

import com.zhuangjb.busy.BusyServer;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.util.SystemUtils;
import com.mongodb.BasicDBObject;

/**
 * 定时统计报表信息
 * 
 * @author test
 *
 */
public class ComputResultTask implements Runnable {

	protected Log log = LogFactory.getLog(this.getClass());

	@Override
	public void run() {

		while (!BusyServer.getInstance().isStopThreadSignal()) {
			log.info("processor start:" + this.getClass().toString());

			BasicDBObject qryfilter2 = new BasicDBObject();
			qryfilter2.put(DBC.dr, 0);

			List<Document> userdocs = MongoDAO.getInstance().find("user",
					qryfilter2);
			for (Document doc : userdocs) {

				try {
					String username = doc.getString("username");
					log.error("username" + username);
					BasicDBObject filter = new BasicDBObject();
					filter.put(DBC.dr, 0);
					filter.put(DBC.username, username);

					String result = "";
					List<Document> list = MongoDAO.getInstance().find(
							"pk10_user_xiazhuflow", filter,
							new BasicDBObject("qishu", -1));
					if (list == null || list.size() < 9) {
						log.error("list null");
						continue;
					}

					if (list.get(0) == null || list.get(1) == null
							|| list.get(2) == null || list.get(3) == null
							|| list.get(4) == null || list.get(5) == null
							|| list.get(6) == null || list.get(7) == null
							|| list.get(8) == null || list.get(9) == null) {
						log.error("list2 null");

						continue;
					}
					log.error("1:" + list.get(0).getString("result"));
					log.error("2:" + list.get(1).getString("result"));
					log.error("3:" + list.get(2).getString("result"));
					String time = list.get(0).getString("ts");

					int x = 0;
					if (list.get(0).getString("result") == null
							|| "".equals(list.get(0).getString("result"))) {
						x = 1;
						log.error("111111");

					} else {
						x = 0;
						log.error("0000000");
					}

					for (int xx = 0; xx <= 9; xx++) {
						log.error("xxxx" + xx + ":"
								+ list.get(xx).getString("result"));
					}

					if ("预测失败".equals(list.get(x).getString("result"))) {

						if ("预测失败".equals(list.get(x + 1).getString("result"))) {
							if ("预测失败".equals(list.get(x + 2).getString(
									"result"))) {
								if ("预测失败".equals(list.get(x + 3).getString(
										"result"))) {
									if ("预测失败".equals(list.get(x + 4)
											.getString("result"))) {
										if ("预测失败".equals(list.get(x + 5)
												.getString("result"))) {
											if ("预测失败".equals(list.get(x + 6)
													.getString("result"))) {
												if ("预测失败".equals(list.get(
														x + 7).getString(
														"result"))) {
													if ("预测失败".equals(list.get(
															x + 8).getString(
															"result"))) {
														if ("预测失败"
																.equals(list
																		.get(x + 9)
																		.getString(
																				"result"))) {
															if ("预测失败"
																	.equals(list
																			.get(x + 10)
																			.getString(
																					"result"))) {
																if ("预测失败"
																		.equals(list
																				.get(x + 11)
																				.getString(
																						"result"))) {
																	result = time
																			+ "连续12次预测失败";

																} else {
																	result = time
																			+ "连续11次预测失败";
																}

															} else {
																result = time
																		+ "连续10次预测失败";
															}
														} else {
															result = time
																	+ "连续9次预测失败";
														}
													} else {
														result = time
																+ "连续8次预测失败";
													}
												} else {
													result = time + "连续7次预测失败";
												}
											} else {
												result = time + "连续6次预测失败";
											}
										} else {
											result = time + "连续5次预测失败";
										}
									} else {
										result = time + "连续4次预测失败";
									}

								} else {
									result = time + "连续3次预测失败";
								}
							} else {
								result = time + "连续2次预测失败";
							}
						} else {
							result = time + "连续1次预测失败";
						}
					}

					if (!"".equals(result)) {
						Document doc2 = new Document();
						doc2.put(username, result);
						MongoDAO.getInstance().findOneAndUpdate("result",
								qryfilter2, new BasicDBObject("$set", doc2));
					}
				}catch (Exception e) {
                   log.error("出错",e);
				}
			}
			SystemUtils.delayTimer(60000);

		}
	}
}
