package com.zqw.le.controller.baseTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zqw.le.conditional.ListService;

@RestController
public class TestConditionalController {
	@Autowired
	private ListService listService;
	@RequestMapping("/testConditional")
	public Object testConditional(){
		return listService.showCmd();
	}
}
