package com.classcost;

import org.jboss.logging.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EnableWebMvc
@MapperScan("com.*.mapper")
@ComponentScan(basePackages = {"com.classcost"})
public class ClassCostApplication extends WebMvcConfigurerAdapter {
	//日志
	private static final Logger log = Logger.getLogger(ClassCostApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ClassCostApplication.class, args);
	}
	
	/**
	 * 解决Swagger2 404
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("swagger2资源定位");
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		
		 registry.addResourceHandler("/webjars/**")
         .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	
	
	

}
