package com.wonders.springcloud.controller;

import com.wonders.springcloud.common.entity.CommonResult;
import com.wonders.springcloud.entity.Payment;
import com.wonders.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qiu
 * @date 2020-07-03 11:02
 */
@RestController
@Slf4j
public class OrderOpenFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    //OpenFeign自带负载均衡配置项
    @GetMapping(value="/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }


    //测试fegin超时
    @GetMapping(value="/consumer/feign/timeout")
    public String paymentFeignTimeout(){
      return paymentFeignService.paymentFeignTimeout();
    }

}
