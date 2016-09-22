package com.zqw.le.controller.baseTest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("value")
public class TestValueAnn {
	@Value("${person.fistName}")
	private String firstName;
	@RequestMapping("/getProperty")
	public String getPoropery(){
		return firstName;
	}
}
