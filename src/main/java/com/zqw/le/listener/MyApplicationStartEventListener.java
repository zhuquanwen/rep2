package com.zqw.le.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
/**服务启动时候的Listener*/
public class MyApplicationStartEventListener implements ApplicationListener<ApplicationStartedEvent>{

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		SpringApplication sa = event.getSpringApplication();
		sa.setShowBanner(false);
		
	}

}
