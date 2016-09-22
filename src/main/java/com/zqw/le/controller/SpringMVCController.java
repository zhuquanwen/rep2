package com.zqw.le.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zqw.le.domain.p.oneMany.Team;
import com.zqw.le.service.TeamService;

@RestController
@RequestMapping("mvc")
public class SpringMVCController {
	@Autowired
	private TeamService teamService;
	@RequestMapping( value={"testOne","testTwo"})
	public String test1(){
		return "1111";
	}
	
	@RequestMapping( value={"/testXML","/testXML1"},produces={"application/xml;charset=UTF-8"})
	public List<Team> testXml(){
		List<Team> ts=teamService.findAll();
		for (int i = 0; i < ts.size(); i++) {
			ts.get(i).setStudent(null);
		}
		return ts;
	}
}
