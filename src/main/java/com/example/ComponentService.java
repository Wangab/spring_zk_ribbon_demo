package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by wanganbang on 7/5/16.
 */
@Service
public class ComponentService {
    private static final Logger loger = Logger.getLogger(ComponentService.class);

    @Resource(name = "restTemplate")
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallBack")
    public String getInfo(){
        return restTemplate.getForObject("http://zk-service", String.class);
    }

    public String fallBack(){
        loger.warn("调用服务失败，短路器执行");
        return "调用服务失败，短路器执行";
    }
}
