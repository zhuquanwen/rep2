package com.zqw.le.listener.newListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoEventPublisher {
	@Autowired
	private ApplicationContext applicationContext;
	
	public void toPublishDemoEvent(){
		applicationContext.publishEvent(new DemoEvent(this,"测试发布一个event"));
	}
	public String getResource(){
		return "获得了一个资源!";
	}
}
