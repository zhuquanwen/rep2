package com.zqw.le.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**spring
 * context已经加载完成，但是bean还没加载完成*/
public class MyApplicationpRreparedEventListener implements ApplicationListener<ApplicationPreparedEvent>{

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {
		 ConfigurableApplicationContext cac = event.getApplicationContext();
		 System.out.println(cac);
		
	}

}
