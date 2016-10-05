package com.zqw.le;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zqw.le.domain.p.jpa.CustomRepositoryFactoryBean;
import com.zqw.le.listener.MyApplicationEnviPreparedEventListener;
import com.zqw.le.listener.MyApplicationStartEventListener;
import com.zqw.le.listener.MyApplicationpRreparedEventListener;
import com.zqw.le.listener.newListener.MyDemoEventListener;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling//开启定时任务功能
@EnableCaching
@EnableJpaRepositories(repositoryFactoryBeanClass=CustomRepositoryFactoryBean.class,
entityManagerFactoryRef="entityManagerFactoryPrimary",transactionManagerRef="transactionManagerPrimary"
,basePackages={"com.zqw.le.domain.p"})
@Import(RepositoryRestMvcConfiguration.class)
public class App 
{
    public static void main( String[] args )
    {
        
        SpringApplication app = new SpringApplication(App.class); 
        app.addListeners(new MyApplicationStartEventListener());
        app.addListeners(new MyApplicationEnviPreparedEventListener());
        app.addListeners(new MyApplicationpRreparedEventListener());
        app.addListeners(new MyDemoEventListener());
        app.run(args);
    }
}
