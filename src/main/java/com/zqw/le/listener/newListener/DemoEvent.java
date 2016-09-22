package com.zqw.le.listener.newListener;

import org.springframework.context.ApplicationEvent;
public class DemoEvent extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	public DemoEvent(DemoEventPublisher source,String msg) {
		super(source);
		this.msg=msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
