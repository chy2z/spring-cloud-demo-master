package cn.demo.service.client.fallback.factory;

import cn.demo.service.client.Service0Client;
import cn.demo.service.controller.ServiceHystrixController;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Leo
 * @Blog: http://blog.csdn.net/lc0817
 * @CreateTime: 2017/3/22 14:44
 * @Description:
 */

/**
 * Hystrix支持失败回退的概念，当线路有错误是则执行默认代码路径，
 * 启用回退要给@FeignClient设置fallback属性来实现回退的类名
 */
@Component
public class Service0FallbackFactory implements FallbackFactory<Service0Client> {

    @Override
    public Service0Client create(final Throwable cause) {
        System.out.println("create:" + cause);
        return new Service0Client() {
            @Override
            public String test(@PathVariable("userId") String userId, @PathVariable("sleepSec") int sleepSec) {
                return "test调用失败:" + userId + "," + sleepSec;
            }

            @Override
            public String user(ServiceHystrixController.User user) {
                return "user调用失败:" + user.toString();
            }

            @Override
            public ServiceHystrixController.User json(ServiceHystrixController.User user) {
                System.out.println("调用失败");
                return user;
            }
        };
    }
}
