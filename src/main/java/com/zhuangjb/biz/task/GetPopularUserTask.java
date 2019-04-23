package com.zhuangjb.biz.task;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zhuangjb.util.HttpUtils;
import com.zhuangjb.util.JsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;

import com.zhuangjb.busy.BusyServer;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.util.SystemUtils;
import com.mongodb.BasicDBObject;

/**
 *
 */
public class GetPopularUserTask implements Runnable {

	protected Log log = LogFactory.getLog(this.getClass());

	@Override
	public void run() {

		while (!BusyServer.getInstance().isStopThreadSignal()) {
			log.info("processor start:" + this.getClass().toString());

			BasicDBObject qryfilter2 = new BasicDBObject();
			qryfilter2.put(DBC.dr, 0);

			List<Document> userdocs = MongoDAO.getInstance().find("user_popular",
					qryfilter2);
			for (Document doc : userdocs) {
				try {
					String json= HttpUtils.httpGet("http://127.0.0.1:5000/instagram/getImageList?username="+doc.get("username"));
					Map<String, Object> resultMap= JsonUtil.jsonToMap(json);
					Document userdoc=new Document();

					for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
						System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
						userdoc.put(entry.getKey(), entry.getValue());
					}
					MongoDAO.getInstance().insert("user_info",userdoc);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			SystemUtils.delayTimer(60000);

		}
	}
}
