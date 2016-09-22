package com.zqw.le.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqw.le.config.TestConfig;


@Service
public class TestService {
	@Resource
	private TestConfig testConfig;
	@Autowired
	public String  test1(){
		return "this is test"+testConfig.getFistName();
	}
}
