package cn.deom.serviceribbon.config;

import cn.deom.serviceribbon.rule.MyLoadBalancerRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

/**
* @Title: MyConfigBean
* @Description: 配置自定义均衡负载算法
* 配合：@ComponentScan(excludeFilters = {
*         @ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeComponentScan.class)
*      })
* 配合：(name="service0",configuration = MyConfigBean.class)
* @author chy
* @date 2018/8/15 11:37
*/
@ExcludeComponentScan
public class MyConfigBean {

    /**
     * 返回自定义的负载算法规则
     * @return
     */
    @Bean
    public IRule myRule() {
        return new MyLoadBalancerRule();
    }
}
