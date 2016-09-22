package com.zqw.le.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("jsp")
public class JspController {
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav=new ModelAndView("index");
		return mav;
	}
}
