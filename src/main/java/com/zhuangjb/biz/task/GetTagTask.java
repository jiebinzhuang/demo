package com.zhuangjb.biz.task;

import com.mongodb.BasicDBObject;
import com.zhuangjb.busy.BusyServer;
import com.zhuangjb.client.InstagramUtils;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.util.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;

import java.util.List;

/**
 *
 */
public class GetTagTask implements Runnable {

	protected Log log = LogFactory.getLog(this.getClass());

	@Override
	public void run() {

		while (!BusyServer.getInstance().isStopThreadSignal()) {
			log.info("processor start:" + this.getClass().toString());

//			InstagramUtils.getTagPost("fashion",24);
//
//			InstagramUtils.getTagPost("beauty",24);
//
//			InstagramUtils.getTagPost("foods",24);
//			InstagramUtils.getTagPost("sports",24);

			InstagramUtils.getTagPost("movie",24);
			InstagramUtils.getTagPost("travel",24);
			InstagramUtils.getTagPost("digital",24);
			InstagramUtils.getTagPost("book",24);
			InstagramUtils.getTagPost("joke",24);
			SystemUtils.delayTimer(600000);

		}
	}
}
