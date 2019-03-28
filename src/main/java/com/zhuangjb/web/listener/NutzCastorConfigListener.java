package com.zhuangjb.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.nutz.castor.Castors;

import com.zhuangjb.web.mvc.Object2BigDecimalCastor;

/**
 * 配置json的自定义转换
 * 
 * @author fangw
 */
public class NutzCastorConfigListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Castors.me().addPaths(Object2BigDecimalCastor.class);
		Castors.me().addCastor(Object2BigDecimalCastor.class);
		// Castors.me().addCastor(XXDateTimeCastor.class);
		// Castors.me().addCastor(XXDoubleCastor.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}
