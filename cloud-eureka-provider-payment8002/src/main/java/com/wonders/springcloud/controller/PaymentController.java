package com.wonders.springcloud.controller;

import com.wonders.springcloud.common.entity.CommonResult;
import com.wonders.springcloud.entity.Payment;
import com.wonders.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qiu
 * @date 2020-05-08 10:26
 */
@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("serverPort:"+serverPort);
        if(result<0){
            return CommonResult.error();
        }
        return CommonResult.success("serverPort:"+serverPort,result);
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment= paymentService.getPaymentById(id);

        return CommonResult.success("serverPort:"+serverPort,payment);

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

    @GetMapping("/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin() {
        return "测试zipkin";
    }
}
