package com.zqw.le.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsynsTestService {
	@Async
	public void test1(){
		System.out.println(11111);
	}
	@Async
	public void test2(){
		System.out.println(22222);
	}
}
