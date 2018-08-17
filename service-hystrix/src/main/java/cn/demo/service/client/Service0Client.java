package cn.demo.service.client;

import cn.demo.service.client.fallback.factory.Service0FallbackFactory;
import cn.demo.service.controller.ServiceHystrixController;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 访问服务  service0
 * 在fallback属性中指定断路器的fallback
 */
@FeignClient(name = "service0", fallbackFactory = Service0FallbackFactory.class)
public interface Service0Client {

    @RequestMapping(method = RequestMethod.GET, path = "user/{userId}/{sleepSec}")
    String test(
            @PathVariable("userId") String userId,
            @PathVariable("sleepSec") int sleepSec
    );

    @RequestMapping(
            method = RequestMethod.POST,
            path = "test"
    )
    String user(
            ServiceHystrixController.User user
    );

    @RequestMapping(
            method = RequestMethod.POST,
            path = "json"
    )
    @ResponseBody
    ServiceHystrixController.User json(
            ServiceHystrixController.User user
    );

}
