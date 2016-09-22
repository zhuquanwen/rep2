package com.zqw.le.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * 服务器环境准备好了，但是context还没有创建的时候*/
public class MyApplicationEnviPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>{

	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		Environment env=event.getEnvironment();
		MutablePropertySources pss=((ConfigurableEnvironment) env).getPropertySources();
		for (PropertySource<?> propertySource : pss) {
			System.out.println(propertySource.getName()+"::"+propertySource.getProperty(propertySource.getName()));
		}
		System.out.println(1111);
	}

}
