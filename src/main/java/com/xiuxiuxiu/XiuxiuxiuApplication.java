package com.xiuxiuxiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class XiuxiuxiuApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(XiuxiuxiuApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(XiuxiuxiuApplication.class);
	}

}