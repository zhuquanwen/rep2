package com.zqw.le.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
	@Scheduled(fixedRate = 5000)
	//每隔5秒执行一次
	public void test1(){
		System.out.println("执行计划任务---fixedRate 5000MS");
	}
	
	@Scheduled(cron = "0 28 11 ? * *")
	//每天11点28分执行
	public void test2(){
		System.out.println("执行计划任务---cron表达式");
	}
}
