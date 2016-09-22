package com.zqw.le.listener.newListener;

import org.springframework.context.ApplicationListener;

public class MyDemoEventListener implements ApplicationListener<DemoEvent>{

	@Override
	public void onApplicationEvent(DemoEvent event) {
		System.out.println(((DemoEventPublisher)event.getSource()).getResource());
		System.out.println(event.getMsg());
		
	}

}
