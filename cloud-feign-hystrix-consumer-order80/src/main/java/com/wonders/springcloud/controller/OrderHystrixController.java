package com.wonders.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wonders.springcloud.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiu
 * @date 2020-07-03 15:53
 */
@RestController
//全局降级方法 只需要有@HystrixCommand即可
@DefaultProperties(defaultFallback = "globalFallBack")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    @HystrixCommand
    public String ok(@PathVariable("id") Integer id){
        String result = paymentHystrixService.ok(id);
        return  result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    //服务降级
    @HystrixCommand(fallbackMethod = "timeoutFallBack",commandProperties = {
            //请求最长超时时间3秒
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String timeout(@PathVariable("id") Integer id){
        String result = paymentHystrixService.timeout(id);
        return  result;
    }

    /**
     *
     * 业务代码和fallback方法混杂在一起 混乱问题
     *
     */
    public String timeoutFallBack(Integer id){
        return "消费者80  请求8001超时或程序运行错误 请稍后再试";
    }

    //解决代码膨胀问题 一个方法对应一个fallBack   下面是全局fallback方法
    public String globalFallBack() {
        return "这是全局异常处理方法";
    }
}
