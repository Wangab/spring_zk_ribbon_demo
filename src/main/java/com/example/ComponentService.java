package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by wanganbang on 7/5/16.
 * @author wanganbang
 * 带hystrix熔断机制和ribbon负载均衡机制的Service
 */
@Service
public class ComponentService {
    private static final Logger loger = Logger.getLogger(ComponentService.class);

    @Resource(name = "restTemplate")
    RestTemplate restTemplate;

    /**
     * 调用集群方法获取信息
     * @return 字符串类型的信息
     */
    @HystrixCommand(fallbackMethod = "fallBack")
    public String getInfo(){
        return restTemplate.getForObject("http://zk-service", String.class);
    }

    /**
     * 当熔断发生时执行该方法进行处理
     * @return  返回错误的返回结果
     */
    public String fallBack(){
        loger.warn("调用服务失败，短路器执行");
        return "调用服务失败，短路器执行";
    }
}
