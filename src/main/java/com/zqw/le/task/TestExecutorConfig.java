package com.zqw.le.task;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//测试多线程

@Configuration
@ComponentScan("com.zqw.le.task")
@EnableAsync

public class TestExecutorConfig implements AsyncConfigurer{

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor tpte=new ThreadPoolTaskExecutor();
		tpte.setCorePoolSize(5);
		tpte.setMaxPoolSize(10);
		tpte.setQueueCapacity(5);
		tpte.initialize();
		return tpte;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
