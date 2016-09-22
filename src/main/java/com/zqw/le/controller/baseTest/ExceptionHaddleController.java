package com.zqw.le.controller.baseTest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*@ControllerAdvice*/
public class ExceptionHaddleController {
	/*@ExceptionHandler(Exception.class)
	public ModelAndView defaultExceptionHandler(HttpServletRequest req, Exception e){
		ModelAndView mav=new ModelAndView("error");
		mav.addObject("url",req.getRequestURI());
		mav.addObject("exception", e);
		return mav;
	}*/
	
}
