package com.wonders.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author qiu
 * @date 2020-07-03 15:22
 */
@Service
public class PaymentService  {

    public String ok(Integer id) {
        return "正常访问 id:"+id;
    }

    //服务降级处理
    //无论是系统报错 或者 服务超时 都会进入fallbackMethod
    //fallbackMethod 如果调用方法失败了 需要有一个错误方法调用
    @HystrixCommand(fallbackMethod = "timeoutFallBack",commandProperties = {
            //表示请求最长时间为3秒 如果3秒请求不成功 就走fallbackMethod
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String timeout(Integer id) {
        int timeNumber = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "耗时(秒):"+timeNumber+" id:"+id;
    }

    public String timeoutFallBack(Integer id){
        return "调用该方法超时请稍后再试,id:"+id;
    }


    /**
     * 服务熔断
     *
     * 10次里面有6次失败了 就触发这个熔断机载
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack",commandProperties = {
            //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //休眠时间窗
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //错误率达到多少跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id < 0){
            throw  new RuntimeException("****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return  "调用成功，流水号：" + serialNumber;
    }


    //调用错误使用的方法 触发熔断之后 需要一段时间之后 才能恢复正常
    public String paymentCircuitBreakerFallBack(Integer id){
        return "id 不能为负数,请稍后再试  id: " + id;
    }

}
