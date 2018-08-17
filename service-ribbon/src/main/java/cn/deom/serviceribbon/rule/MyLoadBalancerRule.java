package cn.deom.serviceribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
* @Title: MyLoadBalancerRule
* @Description: 均衡负载规则
* 配置方式1：代码控制* 方式2：
* 配置方式2：application.yml 中 NFLoadBalancerRuleClassName 指定负载算法
* @author chy
* @date 2018/8/15 10:53
*/
public class MyLoadBalancerRule extends AbstractLoadBalancerRule {


    /**
     * 总共被调用的次数，目前要求每台被调用5次
     */
    private int total = 0;

    /**
     * 当前提供服务的机器号
     */
    private int currentIndex = 0;

    /**
     * 轮询算法
     * total = 0 // 当total==5以后，我们指针才能往下走，
     * index = 0 // 当前对外提供服务的服务器地址，
     * total需要重新置为零，但是已经达到过一个5次，我们的index = 1
     * @param lb
     * @param key
     * @return
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }

            if (total < 5) {
                server = upList.get(currentIndex);
                total++;
            } else {
                total = 0;
                currentIndex++;
                if (currentIndex >= upList.size()) {
                    currentIndex = 0;
                }
            }

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            server = null;
            Thread.yield();
        }

        return server;
    }


    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {}
}
