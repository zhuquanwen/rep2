package com.zqw.le.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfig {

	@Bean(name="config1",initMethod="init",destroyMethod="destroy")
	public TestConfigEntity config1(){
		TestConfigEntity tce=new TestConfigEntity();
		tce.setC1("111");
		tce.setC2("222");
		tce.setC3("333");
		return tce;
	}
	@Bean(name="config2")
	public TestConfigEntity config2(){
		return config1();
	}
	
	@Bean(name="config3")
	public TestConfigEntity config3(TestConfigEntity config2){
		return config2;
	}
	

	
}
