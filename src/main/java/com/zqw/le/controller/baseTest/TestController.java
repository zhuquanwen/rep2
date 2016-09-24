package com.zqw.le.controller.baseTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*import com.zqw.autoConfigTest.service.HaddlerService;*/
import com.zqw.le.aop.Log;
import com.zqw.le.aop.OperateType;
import com.zqw.le.config.TestOtherConfig;
import com.zqw.le.service.TestService;


@RestController
public class TestController {
	@Value("${spring.jpa.properties.hibernate.dialect}") 
    private String dialect;
	
	@Autowired
	private TestOtherConfig testOtherConfig;
	@Autowired
	private TestService testService;
	/*@Autowired
	private HaddlerService haddlerService;*/
	
	@Log(name="测试",remark="测试日志可用性",operateType=OperateType.TEST)
	@RequestMapping("/")
	public String test(){
		return testService.test1();
	}
	@Log(name="跳转至首页",remark="测试跳转至首页的链接",operateType=OperateType.TURNPAGE)
	@RequestMapping("/hasException")
	public String hasError() throws Exception{
		int i=0;
		int x=100/i;
		return "index";
	}
	@RequestMapping("/testValueAn")
	public String testValueAn(){
		return dialect;
	}
	/**
	 * 测试带location的类型安全配置**/
	@RequestMapping("/other")
	public String testOther(){
		return testOtherConfig.getFirstName();
	}
	
	//测试引入自己创建的一个spring boot 工程的自动配置功能
	@RequestMapping("/testOtherBootAutoConfig")
	public String testOtherBootAutoConfig(){
		/*return haddlerService.getMsg();*/
		return null;
	}
}
