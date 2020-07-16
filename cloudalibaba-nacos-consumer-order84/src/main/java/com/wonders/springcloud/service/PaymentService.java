package com.wonders.springcloud.service;

import com.wonders.springcloud.common.entity.CommonResult;
import com.wonders.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * 使用openFeign
 * value:nacos-provider-payment 调用服务名
 * fallback:配置服务降级类
 * @author qiu
 * @date 2020-07-09 10:18
 *
 */
@Component
@FeignClient(value = "nacos-provider-payment",fallback = PaymentFallbackService.class)
public interface PaymentService {


    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult paymentSQL(@PathVariable("id") Long id);

}
