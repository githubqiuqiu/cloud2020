package com.wonders.springcloud.controller;

import com.wonders.springcloud.common.entity.CommonResult;
import com.wonders.springcloud.entity.Payment;
import com.wonders.springcloud.lb.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author qiu
 * @date 2020-05-08 14:03
 */
@Slf4j
@RestController
public class OrderController {
    //private static final String PAYMENT_URL="http://localhost:8001";
    private static final String PAYMENT_URL="http://CLOUD-PROVIDER-PAYMENT";

    //默认是用轮询的方式
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    //自定义loadbalancer
    @Autowired
    MyLoadBalancer myLoadBalancer;


    @PostMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getPaymentById1/{id}")
    public CommonResult getPaymentById1(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }
        return CommonResult.error();
    }


    @GetMapping("/consumer/discovery")
    public CommonResult discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach((e)-> {
            System.out.println("服务:"+e);
            List<ServiceInstance> instances = discoveryClient.getInstances(e);
            instances.forEach((ee)-> System.out.println("节点:"+ee.getInstanceId()+"-"+ee.getHost()+"-"+ee.getPort()));
        });
        return CommonResult.success();
    }


    @GetMapping("/consumer/getPaymentLB")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        if(CollectionUtils.isEmpty(instances)){
            return null;
        }
        URI uri = myLoadBalancer.instance(instances).getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    @GetMapping("/consumer/zipkin")
    public String paymentZipkin() {
        //注意使用了@LoadBalanced 不能直接使用ip:port的方式访问 需要使用服务名访问
        return restTemplate.getForObject( PAYMENT_URL+"/payment/zipkin/",String.class);
    }
}
