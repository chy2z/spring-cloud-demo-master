package cn.demo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 演示Feign，熔断，随机均衡负载
 */
@SpringBootApplication(scanBasePackages = "cn.demo.service")
@EnableFeignClients
@EnableWebMvc
@EnableEurekaClient
//加入Hystrix支持(替代配置文件)
//@EnableCircuitBreaker
public class ServiceHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHystrixApplication.class, args);
    }
}
