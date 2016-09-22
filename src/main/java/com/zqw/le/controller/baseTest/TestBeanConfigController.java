package com.zqw.le.controller.baseTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zqw.le.config.TestConfigEntity;
@RestController
public class TestBeanConfigController {
	@Autowired
	private TestConfigEntity config3;
	@Autowired
	private TestConfigEntity config2;
	
	@Autowired
	private TestConfigEntity config1;
	@RequestMapping("/testConfig1")
	public TestConfigEntity testConfig1(){
		return config1;
	}
	
	@RequestMapping("/testConfig2")
	public TestConfigEntity testConfig2(){
		return config2;
	}
	@RequestMapping("/testConfig3")
	public TestConfigEntity testConfig3(){
		return config3;
	}
}
