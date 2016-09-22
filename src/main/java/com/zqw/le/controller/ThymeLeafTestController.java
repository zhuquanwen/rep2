package com.zqw.le.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zqw.le.domain.p.TestTable1;
import com.zqw.le.domain.p.TestTable1Repository;
import com.zqw.le.domain.p.oneMany.Book;
import com.zqw.le.domain.p.oneMany.Team;
import com.zqw.le.domain.p.secur.Resource;
import com.zqw.le.service.BookService;
import com.zqw.le.service.ResourceService;
import com.zqw.le.service.TeamService;

@Controller
@RequestMapping("thymeleaf")
public class ThymeLeafTestController {
	@Autowired
	private TestTable1Repository testTable1Repository;
	@Autowired
	private BookService bookService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private ResourceService resourceService;
	//测试value
	@RequestMapping("/testValue")
	public ModelAndView testValue(){
		ModelAndView mav=new ModelAndView("thymeleaf/testValue");
		mav.addObject("key", "welcom to china");
		return mav;
	}
	
	//测试text
	@RequestMapping("/testText")
	public ModelAndView testText(){
		ModelAndView mav=new ModelAndView("thymeleaf/testText");
		mav.addObject("key", "welcom to china");
		return mav;
	}
	//测试object *
	@RequestMapping("/testObject")
	public ModelAndView testObject(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testObject");
		TestTable1 tt1=new TestTable1("zhuquanwen");
		mav.addObject("user", tt1);
		return mav;
	}
	//测试@  @{……}支持决定路径和相对路径 其中相对路径又支持跨上下文调用url和协议的引用（//code.jquery.com/jquery-2.0.3.min.js）。
	@RequestMapping("/testAiTe")
	public ModelAndView testJing(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testAiTe");
		mav.addObject("path","http://www.qq.com");
		return mav;
	}
	
	//测试小数点转换
	@RequestMapping("/testDecimal")
	public ModelAndView testDecimal(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testDecimal");
		mav.addObject("price",0.21455545);
		return mav;		
	}
	
	//测试日期转换
	@RequestMapping("/testDateFormat")
	public ModelAndView testDateFormat(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testDateFormat");
		mav.addObject("date",new Date());
		return mav;		
	}
	

	//测试时间戳日期转换
	@RequestMapping("/testTimeStampFormat")
	public ModelAndView testTimeStampFormat(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testDateFormat");
		mav.addObject("date",new Timestamp(System.currentTimeMillis()));
		return mav;		
	}
	
	//测试字符串拼接
	@RequestMapping("/testAddString")
	public ModelAndView addString(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testAddString");
		mav.addObject("str1","string1").addObject("str2", "string2");
		return mav;	
	}
	
	//测试utext 转义显示
	@RequestMapping("/testUtext")
	public ModelAndView testUtext(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testUtext");
		mav.addObject("utext","This is an &lt;em&gt;HTML&lt;/em&gt; text. &lt;b&gt;Enjoy yourself!&lt;/b&gt;");
		return mav;	
	}
	//测试表单
	@RequestMapping("/toFormPage")
	public ModelAndView toFormPage(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testForm");
		mav.addObject("testTable1",new TestTable1("zhu"));
		mav.addObject("formAction","submitForm");
		return mav;
	}
	@RequestMapping("/submitForm")
	public @ResponseBody TestTable1 submitForm(TestTable1 testTable1){
		testTable1Repository.save(testTable1);
		return testTable1;
	}
	//测试数据迭代
	@RequestMapping("/testEach")
	public  ModelAndView testEach(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testEach");
		List<TestTable1> tt1s=testTable1Repository.findAll();
		/*tt1s=null;*/
		mav.addObject("list",tt1s);
		return mav;
		
	}
	//测试小于
	@RequestMapping("/testLt")
	public ModelAndView testLt(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testLt");
		mav.addObject("price", 9);
		mav.addObject("name", "恩");
		return mav;
	}
	//测试switch case
	@RequestMapping("/testSwitch")
	public ModelAndView testSwitch(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testSwitch");
		mav.addObject("name", "x");
		return mav;
	}
	
	//测试 unless //测试没成功
	@RequestMapping("/testUnless")
	public ModelAndView testUnless(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testUnless");
		mav.addObject("name", "x");
		return mav;
	}
	//测试select
	@RequestMapping("/testSelect")
	public ModelAndView testSelect(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testSelect");
		mav.addObject("name", "x");
		return mav;
	}
	//测试Jpa one to many search 通过关联字段
	@RequestMapping("testJpaOneToMSearch2")
	public  ModelAndView testJpaOneToMSearch2(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testJpaOneToMany");
		Book book=bookService.findBookByStudent("5b34848e-4cb8-4d20-9d03-31cc04b8");
		book.setStudent(null);
		mav.addObject("book", book);
		return mav;
	}
	@RequestMapping("testJpaOneToOneFind")
	public ModelAndView testJpaOneToOneFind(){
		ModelAndView mav=new ModelAndView("/thymeleaf/testJpaOneToOneFind");
		List<Team> ts=teamService.findByStudentName("zhuquanwen");
		mav.addObject("list",ts);
		return mav;
	}
	//测试集合是否为空判断，测试JS获取属性
	@RequestMapping("testListEmpty")
	public ModelAndView testListEmpty(){
		ModelAndView mav = new ModelAndView("/thymeleaf/testListEmpty");
		List<Resource> ts = resourceService.findAll();
		mav.addObject("list",ts);
		return mav;
	}
	
}
