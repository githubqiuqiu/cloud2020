package com.wonders.springcloud.controller;

import com.wonders.springcloud.common.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author qiu
 * @date 2020-05-08 10:26
 */
@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/zk")
    public String paymentzk() {
        return "springcloud with zookeeper:" + serverPort + "\t"
                + UUID.randomUUID().toString();
    }

    @GetMapping("/discovery")
    public CommonResult discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach((e)-> {
            System.out.println("服务:"+e);
            List<ServiceInstance> instances = discoveryClient.getInstances(e);
            instances.forEach((ee)-> System.out.println("节点:"+ee.getInstanceId()+"-"+ee.getHost()+"-"+ee.getPort()));
        });
        return CommonResult.success();
    }
}
