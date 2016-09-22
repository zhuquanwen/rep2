package com.zqw.le.conditional;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCondition {
	@Conditional(Condition1.class)
	@Bean
	public ListService windowsListService(){
		return new WindowsListService();
	}
	
	@Conditional(Condition2.class)
	@Bean
	public ListService linuxListService(){
		return new LinuxListService();
	}

}
