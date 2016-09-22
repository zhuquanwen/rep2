package com.zqw.le.controller.baseTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zqw.le.task.AsynsTestService;

@RestController
@RequestMapping("task")
public class TestTaskController {
	@Autowired
	private AsynsTestService asynsTestService;
	@RequestMapping("/testAsync")
	public Object testAsync() throws InterruptedException{
		for (int i = 0; i < 100; i++) {
			asynsTestService.test1();
			asynsTestService.test2();
			Thread.currentThread().sleep(500);
		}
		return 1111;
	}
}
