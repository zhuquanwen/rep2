package com.zqw.le.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqw.le.domain.p.moreTS.A;
import com.zqw.le.service.AService;

@Controller
@RequestMapping("moreTS")
public class MoreTableSelectController {
	@Autowired
	private AService aService;
	@RequestMapping(value="/test",produces={"application/json;charset=UTF-8"})
	public @ResponseBody List<A> test(){
		return aService.findAs();
	}
}
