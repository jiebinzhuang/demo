package com.zhuangjb.system;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhuangjb.busy.BusyServer;
import com.zhuangjb.util.ConfigUtils;
import com.zhuangjb.util.ConfigVO;
import com.zhuangjb.util.SystemUtils;
import com.zhuangjb.web.server.WebServer;
import com.zhuangjb.web.server.WebServerConfigUtils;

public class MainUI extends JFrame {
	private static final long serialVersionUID = 2624811667768995189L;

	protected final Log logger = LogFactory.getLog(getClass());
	private static MainUI mainUI;

	/**
	 * 主界面的信息显示区
	 */
	final private JTextArea infoTextArea;

	/**
	 * 嵌入式web服务器(jetty)
	 */
	final private WebServer webServer;

	/**
	 * 图片资源
	 */
	private final ImageIcon image_start = new ImageIcon(this.getClass().getResource("/images/start.png"));
	private final ImageIcon image_stop = new ImageIcon(this.getClass().getResource("/images/stop.png"));
	private final ImageIcon image_config = new ImageIcon(this.getClass().getResource("/images/config.png"));
	private final ImageIcon image_exit = new ImageIcon(this.getClass().getResource("/images/exit.png"));

	final private JButton btn_start_stop = new JButton("启动", image_start);
	final private JButton btn_config = new JButton("管理后台", image_config);
	final private JButton btn_exit = new JButton("退出", image_exit);

	public static MainUI getMainFrame() {
		return mainUI;
	}

	public JTextArea getInfoTextArea() {
		return infoTextArea;
	}

	public WebServer getWebServer() {
		return webServer;
	}

	public ImageIcon getImage_start() {
		return image_start;
	}

	public ImageIcon getImage_stop() {
		return image_stop;
	}

	public ImageIcon getImage_config() {
		return image_config;
	}

	public ImageIcon getImage_exit() {
		return image_exit;
	}

	public JButton getBtn_start_stop() {
		return btn_start_stop;
	}

	public JButton getBtn_admin() {
		return btn_config;
	}

	public JButton getBtn_exit() {
		return btn_exit;
	}

	public MainUI() {
		this.setLayout(new BorderLayout());

		ConfigVO config = ConfigUtils.getConfig();
		this.setTitle(config.getTitle() + "-" + WebServerConfigUtils.getWebServerConfig().getPort());

		this.setSize(config.getWidth(), config.getHeight());

		infoTextArea = new JTextArea();
		// infoTextArea.setSize(arg0, arg1);
		infoTextArea.setEditable(false);

		// 检测管理服务端口是否被占用(如果被占用，直接提示个swing对话框，确认后退出)
		if (!SystemUtils.isPortOk(WebServerConfigUtils.getWebServerConfig().getPort())) {
			System.exit(0);
		}

		// 启动嵌入是web服务
		webServer = new WebServer();
		Thread adminServerThread = new Thread(webServer);
		adminServerThread.start();

		this.init();
		this.setVisible(true);

		// setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation(width / 2 - 150, height / 2 - 100);

		/**
		 * 点关闭时弹出确认对话框
		 */
		// 设置关闭时什么也不做
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// 监听关闭按钮的点击操作
		this.addWindowListener(new WindowAdapter() {
			// 覆盖windowClosing方法
			@Override
			public void windowClosing(WindowEvent e) {
				SystemUtils.exit();
			}
		});

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				/**
				 * 系统配置初始化
				 */
				SystemUtils.info("初始化数据库连接,请稍后...");
				if (SystemUtils.testDatabase()) {
					SystemUtils.info("数据库连接初始化成功.");
					btn_start_stop.setEnabled(true);
				} else {
					SystemUtils.error("数据库连接初始化失败,系统无法正常工作,请立即检查配置!!!");
				}
			}
		});
		thread.start();
	}

	private void init() {
		btn_start_stop.setEnabled(false);
		btn_start_stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (BusyServer.getInstance().isRunning()) {
					if (JOptionPane.showConfirmDialog(MainUI.getMainFrame(), "确定停止业务服务吗?", "提示",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						SystemUtils.stopBusyServer();
					}
				} else {
					SystemUtils.startBusyServer();
				}
			}
		});
		btn_config.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.getDesktop();
				try {
					String url = "http://";
					if ("0.0.0.0".equalsIgnoreCase(WebServerConfigUtils.getWebServerConfig().getHostname())) {
						url += "localhost";
					} else {
						url += WebServerConfigUtils.getWebServerConfig().getHostname();
					}
					if (WebServerConfigUtils.getWebServerConfig().getPort() != 80) {
						url += ":" + WebServerConfigUtils.getWebServerConfig().getPort();
					}
					url += "/index.action";
					desktop.browse(new URI(url));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SystemUtils.exit();
			}
		});

		JToolBar toolbar = new JToolBar("JToolBar");
		toolbar.add(btn_start_stop);
		toolbar.add(btn_config);
		toolbar.add(btn_exit);
		this.add(toolbar, BorderLayout.PAGE_START);

		JScrollPane scrollPane = new JScrollPane(infoTextArea);
		this.add(scrollPane, BorderLayout.CENTER);

		JLabel label = new JLabel("技术支持QQ：271503536");
		JToolBar buttomBar = new JToolBar();
		buttomBar.add(label);
		this.add(buttomBar, BorderLayout.SOUTH);
	}

	public static void main(String args[]) {
		MainUI.mainUI = new MainUI();
	}

}
