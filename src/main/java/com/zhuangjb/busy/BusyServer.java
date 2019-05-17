package com.zhuangjb.busy;

import java.util.ArrayList;
import java.util.List;

import com.zhuangjb.biz.task.GetTagTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhuangjb.biz.task.GetPopularUserTask;
import com.zhuangjb.util.SystemUtils;

/**
 * 业务服务
 * 
 * @author zhuangjb
 */
public class BusyServer {
	private static final Log log = LogFactory.getLog(BusyServer.class);

	/**
	 * 单例模式
	 */
	private static BusyServer busyServer = null;

	/**
	 * 运行状态
	 */
	private boolean isRunning = false;

	/**
	 * 线程停止信号,这里用了volatile关键字,保证多线程时不会有问题
	 */
	volatile private boolean stopThreadSignal = false;

	private final List<Thread> threadPool = new ArrayList<Thread>();

	private BusyServer() {
		// 禁止直接实例化
	}

	public synchronized static BusyServer getInstance() {
		if (busyServer == null) {
			busyServer = new BusyServer();
		}
		return busyServer;
	}

	public synchronized boolean startServer() {
		try {
			if (this.isRunning()) {
				throw new RuntimeException("服务运行中,状态不正常,业务服务无法启动!");
			}


			// 设置线程停止信号为false
			this.setStopThreadSignal(false);


			GetPopularUserTask getdatatask = new GetPopularUserTask();
			Thread getdatathread = new Thread(getdatatask);
			getdatathread.setName("GetPopularUserTask");
			threadPool.add(getdatathread);


//			GetTagTask tagask = new GetTagTask();
//			Thread tagaskthread = new Thread(tagask);
//			tagaskthread.setName("GetTagTask");
//			threadPool.add(tagaskthread);

			this.setRunning(true);

			for (Thread thread : threadPool) {
				thread.start();
			}

			return true;
			
		} catch (Exception ex) {
			log.error("业务服务启动失败!", ex);
			SystemUtils.error(ex.getMessage());
		}
		return false;
	}

	public synchronized boolean stopServer() {
		try {
			if (!this.isRunning()) {
				throw new RuntimeException("业务服务目前没有运行!");
			}

			// 设置线程停止信号为false
			this.setStopThreadSignal(true);

			// 业务处理
			for (Thread thread : threadPool) {
				while (thread.isAlive()) {
					log.debug("等待进程[" + thread.getName() + " ]停止...");
					SystemUtils.delayTimer(1000);
				}
			}

			threadPool.clear();

			this.setRunning(false);
			return true;
		} catch (Exception ex) {
			log.error("业务服务停止出错!", ex);
			SystemUtils.error(ex.getMessage());
		}
		return false;
	}

	public boolean isRunning() {
		return isRunning;
	}

	private synchronized void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isStopThreadSignal() {
		return stopThreadSignal;
	}

	public synchronized void setStopThreadSignal(boolean stopThreadSignal) {
		this.stopThreadSignal = stopThreadSignal;
	}

}
