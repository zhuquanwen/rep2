package com.zqw.le.aware;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
@Component
public class TestAware implements BeanNameAware,ResourceLoaderAware{
	private ResourceLoader rl;
	private String beanName;
	@Override
	public void setResourceLoader(ResourceLoader rl) {
		this.rl=rl;
	}

	@Override
	public void setBeanName(String beanName) {
		this.beanName=beanName;
	}

	public String getResource() throws IOException{
		String result = null;
		Resource r = rl.getResource("classpath:test.txt");
		String fileText = IOUtils.toString(r.getInputStream());
		result="beanName:"+beanName+";;;资源内容:"+fileText;
		return result;
	}
}
