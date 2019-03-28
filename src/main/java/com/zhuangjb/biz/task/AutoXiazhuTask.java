package com.zhuangjb.biz.task;

import java.util.HashMap;
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
 * 自動下注
 * @author test
 *
 */
public class AutoXiazhuTask implements Runnable {

	protected Log log = LogFactory.getLog(this.getClass());

	public String getCollectionName() {
		return "xiazhu_info";
	}
	protected MongoDAO dao = MongoDAO.getInstance();
	
	
	public void run() {
		log.info("processor start:" + this.getClass().toString());
		try {
			
			HashMap<String,Thread> xiazhuthreadPool = new HashMap<String,Thread>();
			
			
			
			// 从队列获取消息
			while (!BusyServer.getInstance().isStopThreadSignal()) {
				

				BasicDBObject qryfilter = new BasicDBObject();
				qryfilter.put(DBC.dr, 0);
//				qryfilter.append("xiazhudate", DateUtils.getCurrentDate());
				List<Document> list = dao.find(getCollectionName(), qryfilter);
				

				log.info("等待处理下一波即将下注的小伙伴们:" + this.getClass().toString());
				SystemUtils.delayTimer(10000);
				
				
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "出现异常,线程将退出!", e);
			SystemUtils.error(this.getClass().getName() + "出现异常,线程将退出!");
			throw new RuntimeException(e);
		}

		/**
		 * 线程退出
		 */
		log.info("processor end:" + this.getClass().toString());
	}

}
