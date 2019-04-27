package com.zhuangjb.biz.task;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zhuangjb.client.InstagramUtils;
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

			List<Document> userdocs = MongoDAO.getInstance().find("user_popular",
					qryfilter2);
			InstagramUtils.getUserPost("beyonce",1);
//			for (Document doc : userdocs) {
//				try {
//					InstagramUtils.updateUserInfo((String)doc.get("username"));
//
//					InstagramUtils.getUserPost((St	ring)doc.get("username"),1);
//				} catch (Exception e1) {
//					log.error("update data error",e1);
//				}
//			}
			SystemUtils.delayTimer(600000);

		}
	}
}
