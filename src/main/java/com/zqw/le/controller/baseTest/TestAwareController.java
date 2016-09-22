package com.zqw.le.controller.baseTest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zqw.le.aware.TestAware;

@RestController
@RequestMapping("testAware")
public class TestAwareController {
	@Autowired
	private TestAware testAware;
	@RequestMapping("/test")
	public String test() throws IOException{
		return testAware.getResource();
	}
}
