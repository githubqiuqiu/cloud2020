package com.wonders.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wonders.springcloud.common.entity.CommonResult;
import com.wonders.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author qiu
 * @date 2020-07-08 18:41
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static  final  String SERVICE_URL = "http://nacos-provider-payment";

    //使用ribbin调用
    @Autowired
    private RestTemplate restTemplate;

    //使用openFeign调用
    @Autowired
    private PaymentService paymentService;


    /**
     *
     * 如果程序运行时异常和sentinel异常同时存在  先进入sentinel配置异常的处理
     * fallbackClass:程序运行异常的异常处理类
     * fallback:程序运行异常的异常处理方法
     *
     * blockHandlerClass:sentinel配置异常的异常处理类
     * blockHandler:sentinel配置异常的异常处理方法
     *
     */


    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")
    //@SentinelResource(value = "fallback",fallback ="handlerFallback")
    @SentinelResource(value = "fallback",fallback ="handlerFallback",blockHandler = "blockHandler")
    public CommonResult fallback(@PathVariable Long id) {


        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class,id);

        if(id == 4){
            throw new IllegalArgumentException("IllegalArgument ,非法参数异常...");
        }else if(result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }

        return  result;
    }

    public CommonResult handlerFallback(@PathVariable Long id,Throwable e) {
        return  CommonResult.error(444,"异常handlerFallback，exception内容： " + e.getMessage());
    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException e) {
        return  CommonResult.error(444,"blockHandler-sentinel 限流，BlockException： " + e.getMessage());
    }

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
