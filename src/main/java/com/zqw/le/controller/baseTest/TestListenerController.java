package com.zqw.le.controller.baseTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zqw.le.listener.newListener.DemoEventPublisher;

@RestController
@RequestMapping("testListener")
public class TestListenerController {
	@Autowired
	private DemoEventPublisher demoEventPublisher;
	@RequestMapping("/test")
	public Object test(){
		demoEventPublisher.toPublishDemoEvent();
		return 111111;
	}
}
