package com.zhuangjb.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.ServerSocket;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.mortbay.jetty.Server;

import com.zhuangjb.busy.BusyServer;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.system.MainUI;
import com.zhuangjb.web.server.WebServer;
import com.mongodb.BasicDBObject;

public class SystemUtils {
	private static final Logger log = Logger.getLogger(SystemUtils.class);
	private static Properties databaseProperties = null;

	public static Properties getDatabaseProperties() {
		if (databaseProperties == null) {
			databaseProperties = new Properties();
			try {
				InputStream is = SystemUtils.class.getResourceAsStream("/database.properties");
				databaseProperties.load(is);
			} catch (Exception e) {
				throw new RuntimeException("license error!", e);
			}
		}
		return databaseProperties;
	}

	public static boolean testDatabase() {
		MongoDAO.getInstance().count("test", new BasicDBObject(DBC.dr, 0));
		return true;
	}

	public static void info(String msg) {
		MainUI.getMainFrame().getInfoTextArea().append(DateUtils.getTS() + " " + msg + "\n");
		log.info(msg);
	}

	public static void error(String msg) {
		String message = DateUtils.getTS() + " " + msg + "\n";
		MainUI.getMainFrame().getInfoTextArea().append(message);
		log.error(msg);
	}

	public static void errorAndAlert(String msg) {
		log.error(msg);
		String message = DateUtils.getTS() + " " + msg + "\n";
		MainUI.getMainFrame().getInfoTextArea().append(message);
		JOptionPane.showMessageDialog(null, message, "系统错误", JOptionPane.YES_OPTION);
	}

	public static void stopBusyServer(Writer out) throws IOException {
		out.write("<div>准备停止业务服务器...</div>");
		SystemUtils.stopBusyServer();
		out.write("<div>业务服务器已停止.</div>");
	}

	public static void startBusyServer(Writer out) throws IOException {
		out.write("<div>准备启动业务服务器...</div>");
		SystemUtils.startBusyServer();
		out.write("<div>业务服务器已启动.</div>");
	}

	public static void restartAdminServer(Writer out) throws Exception {
		WebServer webServer = MainUI.getMainFrame().getWebServer();
		Server server = webServer.getHttpServer();

		out.write("<div>准备停止管理服务器:</div>");
		if (server.isRunning()) {
			try {
				out.write("<div>管理服务器当前处于运行状态.</div>");
				out.write("<div>管理服务器停止中,等下将需要重新登录!</div>");
				out.write("</body></html><script type=\"text/javascript\">window.location.href='login'</script>");
				out.flush();
				server.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		int count = 0;
		while (server.isStopping()) {
			count++;
			if (count > 1000) {
				log.warn("管理服务器可能停止失败!");
				break;
			}
			SystemUtils.delayTimer(1000);
		}

		if (server.isStopped()) {
			server.start();
		}
	}

	/**
	 * 检测指定端口是否被占用
	 * 
	 * @param port
	 * @return
	 */
	public static boolean isPortOk(int port) {
		try {
			ServerSocket serversocket = new ServerSocket(port);
			serversocket.close();
			return true;
		} catch (Exception e1) {
			String errMsg = "端口[" + port + "]已被占用,软件可能已经运行,请不要同时打开多个!";
			log.error(errMsg, e1);
			JOptionPane.showMessageDialog(null, errMsg, "系统启动失败", JOptionPane.YES_OPTION);
		}
		return false;
	}

	/**
	 * 启动业务服务(这里会同步修改swing端的状态，建议web和swing端都调用这个)
	 */
	public static void startBusyServer() {
		MainUI mainFrame = MainUI.getMainFrame();
		JButton btn_start_stop = mainFrame.getBtn_start_stop();

		if (!BusyServer.getInstance().isRunning()) {
			// 启动业务服务
			if (BusyServer.getInstance().startServer()) {
				btn_start_stop.setText("停止");
				btn_start_stop.setIcon(mainFrame.getImage_stop());
				SystemUtils.info("业务服务已启动.");
			}
		}
	}

	/**
	 * 停止业务服务(这里会同步修改swing端的状态，建议web和swing端都调用这个)
	 */
	public static void stopBusyServer() {
		MainUI mainFrame = MainUI.getMainFrame();
		JButton btn_start_stop = mainFrame.getBtn_start_stop();

		if (BusyServer.getInstance().isRunning()) {
			// 关闭业务服务
			if (BusyServer.getInstance().stopServer()) {
				btn_start_stop.setText("启动");
				btn_start_stop.setIcon(mainFrame.getImage_start());

				SystemUtils.info("业务服务已停止.");
			}
		}
	}

	public static void exit() {
//		if (BusyServer.getInstance().isRunning()) {
//			// 业务服务运行中，弹出警告提示
//			if (JOptionPane.showConfirmDialog(MainUI.getMainFrame(), "业务运行中,强制退出吗?", "提示", JOptionPane.YES_NO_OPTION,
//					JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
//				try {
//					if (BusyServer.getInstance().isRunning()) {
//						SystemUtils.stopBusyServer();
//
//						while (BusyServer.getInstance().isRunning()) {
//							SystemUtils.delayTimer(1000);
//						}
//						doExit();
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		} else {
			doExit();
//		}
	}

	private static void doExit() {
		// try {
		// OrderTaskListener.timer.cancel();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// delayTimer(3000);

		// Dao dao = new Dao("jdbc/ROEEE");
		// while (!TxlAction.queue.isEmpty() && TxlAction.queue.size() > 0) {
		// List<TxlVO> list = TxlAction.queue.poll();
		// if (list == null) {
		// break;
		// } else {
		// for (TxlVO vo : list) {
		// vo.setDr(0);
		// vo.setVOStatus(VOStatus.更新);
		// }
		// dao.saveOrUpdate(list);
		// }
		// }

		WebServer adminServer = MainUI.getMainFrame().getWebServer();
		Server server = adminServer.getHttpServer();
		if (server.isRunning()) {
			try {
				server.stop();

				int count = 0;
				while (!server.isStopped() && server.isStopping()) {
					count++;
					if (count > 1000) {
						log.warn("管理服务器停止可能失败!");
						break;
					}
					SystemUtils.delayTimer(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 了无牵挂,退出吧
		System.exit(0);
	}

	/**
	 * 延时器(单位:ms)
	 * 
	 * @param time
	 */
	public static void delayTimer(Integer time) {
		if (time <= 0) {
			return;
		}
		long t = time / 10;
		if (t == 0) {
			t = 1;
		}
		for (int i = 0; i < t; i++) {
			try {
				Thread.sleep(10);

				// if (BusyServer.getInstance().isStopThreadSignal()) {
				// break;
				// }
			} catch (InterruptedException e) {
			}
		}
	}
}
