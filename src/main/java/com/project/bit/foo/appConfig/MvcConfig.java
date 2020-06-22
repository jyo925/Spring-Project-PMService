package com.project.bit.foo.appConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer  {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String uploadDirectory = System.getProperty("user.dir")+"/uploads";
		registry.addResourceHandler("/img/**")
				.addResourceLocations("file:/"+uploadDirectory+"/");
		registry.addResourceHandler("/img/test/**")
				.addResourceLocations("file:/"+uploadDirectory+"/");
		registry.addResourceHandler("/img/HHH**")
				.addResourceLocations("file:/"+uploadDirectory+"/");
	}
	
	public MvcConfig() {
		// TODO Auto-generated constructor stub
	}
	
}
