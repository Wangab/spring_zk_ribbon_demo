package com.example;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

/**
 * Created by wanganbang on 7/4/16.
 */
@RestController
public class ZKRibbonContorller {

//    @Resource
//    private LoadBalancerClient loadBalancerClient;
    @Resource
    DiscoveryClient discoveryClient;
    @Resource
    ComponentService componentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getInfo() {
        System.out.println(discoveryClient.getInstances("zk-service"));
        return "这是一个有Ribbon负载均衡和Hystrix熔断机制的Zookeeper为注册中心的服务";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getStr(){
//        URI uri = loadBalancerClient.choose("zk-service").getUri();
//        System.out.println(uri);
//        return restTemplate.getForObject(uri, String.class);
        return componentService.getInfo();
    }
}
