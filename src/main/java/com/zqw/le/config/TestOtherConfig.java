package com.zqw.le.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/***
 * 测试取其他location的数据,给予类型安全的配置
 * */
@Component
@ConfigurationProperties(prefix="test",locations={"classpath:otherConfig.properties"})
public class TestOtherConfig {
	private String firstName;
	private String lastName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
