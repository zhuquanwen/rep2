package com.zqw.le.controller.baseTest;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zqw.le.domain.p.oneMany.Student;
import com.zqw.le.service.StudentService;

@Controller
@RequestMapping("push")
public class PushController {
	@Autowired
	private StudentService studentService;
	@RequestMapping(value="/toSse")
	public String tosse(){
		return "thymeleaf/seePush";
	}
/*	@SuppressWarnings("static-access")
	@RequestMapping(value="/sse",produces={"text/event-stream"})
	@ResponseBody
	public String sse(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "data:" + Math.random()+"";
	}*/
	@RequestMapping(value="/sse",produces="text/event-stream")
    public @ResponseBody String push() throws JsonProcessingException{
		Random r = new Random();
        try {
                Thread.sleep(5000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
        List<Student> students = studentService.findAll();
        for (int i = 0; i < students.size(); i++) {
			students.get(i).setAuthors(null);
			students.get(i).setBooks(null);
			students.get(i).setTeam(null);
		}
        ObjectMapper mapper = new ObjectMapper(); 
        String ss=mapper.writeValueAsString(students);
       return "data:" +  ss + "\n\n";
    }
}
