package com.ancao.treasures;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@ServletComponentScan
@MapperScan("com.ancao.treasures.mapper")
@SpringBootApplication
@ImportResource(locations = { "classpath:mykaptcha.xml" })
public class SysBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SysBootApplication.class, args);
	}
}
