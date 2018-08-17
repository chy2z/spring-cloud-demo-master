package cn.deom.serviceribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/user/{userId}/{sleepSec}",produces = "text/plain;charset=UTF-8")
    public String findById(@PathVariable String userId,@PathVariable int sleepSec) {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("service0");
        System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"
                + serviceInstance.getPort());// 打印当前调用服务的信息
        String result = this.restTemplate.getForObject("http://service0/user/" + userId + "/" + sleepSec, String.class);
        System.out.println(result);
        return result;
    }
}
