package com.zqw.le.controller.baseTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping(value="/toIndex")
	public ModelAndView toIndex(){
		ModelAndView mav=new ModelAndView("index");
		mav.addObject("test", 111);
		return mav;
	}
}
