package com.zhuangjb.biz.task;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhuangjb.busy.BusyServer;
import com.zhuangjb.util.SystemUtils;

/**
 * 定时开一下浏览器
 * 
 * @author test
 *
 */
public class OpenBrowersTask implements Runnable {

	protected Log log = LogFactory.getLog(this.getClass());

	@Override
	public void run() {

		while (!BusyServer.getInstance().isStopThreadSignal()) {
			log.info("processor start:" + this.getClass().toString());
			SystemUtils.delayTimer(1000);
			String url = "http://20131.988cc.cc/";

			try {
				Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler " + url);
			} catch (IOException e) {
				log.error("开浏览器异常", e);
			}
			SystemUtils.delayTimer(10000000);
		}
		/**
		 * 线程退出
		 */
		log.info("processor end:" + this.getClass().toString());
	}

}
