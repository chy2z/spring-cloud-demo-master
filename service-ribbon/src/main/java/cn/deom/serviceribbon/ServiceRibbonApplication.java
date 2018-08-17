package cn.deom.serviceribbon;

import cn.deom.serviceribbon.config.ExcludeComponentScan;
import cn.deom.serviceribbon.config.MyConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 演示ribbon均衡负载
 */
@SpringBootApplication(scanBasePackages ="cn.deom.serviceribbon")
@EnableEurekaClient
/**
 * 扫描包时排除自定义均衡负载算法，防止全局使用
 */
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeComponentScan.class)})
/**
 * 指服务名称采用的均衡负载算法
 */
@RibbonClient(name="service0",configuration = MyConfigBean.class)
public class ServiceRibbonApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceRibbonApplication.class, args);
	}
}
