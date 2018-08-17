package cn.demo.service0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 测试服务的均衡负载
 */
@SpringBootApplication(scanBasePackages = "cn.demo.service0")
@EnableEurekaClient
@EnableWebMvc
public class Service0Application {

    public static void main(String[] args) {
        SpringApplication.run(Service0Application.class, args);
    }
}
