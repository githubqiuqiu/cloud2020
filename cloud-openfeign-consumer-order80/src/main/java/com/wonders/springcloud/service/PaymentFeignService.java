package com.wonders.springcloud.service;

import com.wonders.springcloud.common.entity.CommonResult;
import com.wonders.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Base64;

/**
 * @author qiu
 * @date 2020-07-03 10:56
 */
@Component
//这个value 就是调用服务名称
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT")
public interface PaymentFeignService {

    //这个value 是调用服务的请求地址
    @GetMapping(value="/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);


    //超时接口
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();

}