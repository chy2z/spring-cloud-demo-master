package com.example.serviceversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 测试自定义路由提供微服务
 */
@SpringBootApplication
@EnableEurekaClient
@EnableWebMvc
public class ServiceVersionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceVersionApplication.class, args);
	}
}
