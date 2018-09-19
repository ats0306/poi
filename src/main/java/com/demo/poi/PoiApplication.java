package com.demo.poi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @EnableTransactionManagement 如果mybatis中service实现类中加入事务注解，需要此处添加该注解
 * 
 * @MapperScan //扫描的是mapper.xml中namespace指向值的包位置
 * @author Administrator
 *
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.demo.poi.mapper")
public class PoiApplication extends SpringBootServletInitializer {
	
	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PoiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PoiApplication.class, args);
	}
}
