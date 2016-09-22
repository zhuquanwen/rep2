package com.zqw.le.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("bootstrap")
public class TestBootStrapController {
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("thymeleaf/bootstrap/index");
		return mav;
	}
	
	@RequestMapping("/test1")
	public ModelAndView test1(){
		ModelAndView mav = new ModelAndView("thymeleaf/bootstrap/test1");
		return mav;
	}
	
	@RequestMapping("/test2")
	public ModelAndView test2(){
		ModelAndView mav = new ModelAndView("thymeleaf/bootstrap/test2");
		return mav;
	}
	@RequestMapping("/test3")
	public ModelAndView test3(){
		ModelAndView mav = new ModelAndView("thymeleaf/bootstrap/test3");
		return mav;
	}
	@RequestMapping("/test4")
	public ModelAndView test4(){
		ModelAndView mav = new ModelAndView("thymeleaf/bootstrap/test4");
		return mav;
	}
	@RequestMapping("/test5")
	public ModelAndView test5(){
		ModelAndView mav = new ModelAndView("thymeleaf/bootstrap/test5");
		return mav;
	}
}
