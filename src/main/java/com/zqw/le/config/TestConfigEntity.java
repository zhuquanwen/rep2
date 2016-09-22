package com.zqw.le.config;

public class TestConfigEntity {
	private String c1;
	private String c2;
	private String c3;
	public String getC1() {
		return c1;
	}
	public void setC1(String c1) {
		this.c1 = c1;
	}
	public String getC2() {
		return c2;
	}
	public void setC2(String c2) {
		this.c2 = c2;
	}
	public String getC3() {
		return c3;
	}
	public void setC3(String c3) {
		this.c3 = c3;
	}
	//测试初始化和销毁bean,使用jsr250
//	@PostConstruct
//	public void init(){
//		System.out.println("正在进行初始化..");
//	}
//	@PreDestroy
//	public void destroy(){
//		System.out.println("正在进行销毁...");
//	}
	public void init(){
		System.out.println("正在进行初始化..");
	}
	public void destroy(){
		System.out.println("正在进行销毁...");
	}
}
