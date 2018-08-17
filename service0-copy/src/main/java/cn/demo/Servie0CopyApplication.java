package cn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 测试服务的均衡负载
 */
@SpringBootApplication(scanBasePackages = "cn.demo.service01")
@EnableEurekaClient
@EnableWebMvc
public class Servie0CopyApplication {
	public static void main(String[] args) {
		SpringApplication.run(Servie0CopyApplication.class, args);
	}
}