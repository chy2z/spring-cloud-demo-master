package com.example.customergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * 自定义网关路由
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class CustomergatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomergatewayApplication.class, args);
	}

	/**
	 * 使用regexmapper提供serviceId和routes之间的绑定.
	 * 它使用正则表达式组来从serviceId提取变量, 然后注入到路由表达式中。
	 *
	 * 这个意思是说
	 * serviceId: service-version
	 * 将会匹配路由"/version/service/**".
	 * 任何正则表达式都可以, 但是必须存在于servicePattern和routePattern之中.
	 *
	 * 默认访问:
	 * 自定义路由
	 * http://localhost:8084/version/service/test
	 *
	 * 配置路由
	 * http://localhost:8084/service/version/test
	 *
	 * 加上 prefix 前缀后访问：
	 * 自定义路由
	 * http://localhost:8084/api/version/service/test
	 * 配置路由
	 * http://localhost:8084/api/service/version/test
	 *
	 * @return
	 */
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)",
				"${version}/${name}");
	}
}
