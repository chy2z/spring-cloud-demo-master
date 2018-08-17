package cn.demo.reverseProxy.config;

import cn.demo.reverseProxy.filter.AccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @Title: ConfigBean
* @Description: 创建bean
* @author chy
* @date 2018/8/15 16:20
*/
@Configuration
public class ConfigBean {

    /**
     * 获取过滤
     * @return
     */
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

}
