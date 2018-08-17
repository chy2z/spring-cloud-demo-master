package cn.deom.serviceribbon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
* @Title: ConfigBean
* @Description: 初始化bean
* @author chy
* @date 2018/8/15 10:22
*/
@Configuration
public class ConfigBean {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @LoadBalanced
    // 添加负载均衡支持，很简单，只需要在RestTemplate上添加@LoadBalanced注解，
    // 那么RestTemplate即具有负载均衡的功能,如果不加@LoadBalanced注解的话，
    // 会报java.net.UnknownHostException:service0异常，
    // 此时无法通过注册到Eureka Server上的服务名来调用服务，
    // 因为RestTemplate是无法从服务名映射到ip:port的，
    // 映射的功能是由LoadBalancerClient来实现的。
    public RestTemplate restTemplate() {
        return builder.build();
    }

    /**
     * 切换Ribbon中赋值均衡的规则，而不是使用默认的轮询方式
     * 如果配置的话，所有微服务都使用一样的均衡负载规则
     * (慎重!!!!!!!!!!!!!)
    @Bean
    public IRule MyRule(){
        return new RandomRule();
    }
     */
}
