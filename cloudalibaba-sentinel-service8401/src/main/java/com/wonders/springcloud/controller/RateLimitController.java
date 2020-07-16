package com.wonders.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wonders.springcloud.common.entity.CommonResult;
import com.wonders.springcloud.entity.Payment;
import com.wonders.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiu
 * @date 2020-07-08 17:17
 */
@RestController
public class RateLimitController {
    /**
     *
     * 注意 在sentinel后台配置时 需要在@SentinelResource的value值后面配置
     * 虽然@GetMapping 后面请求地址也能配置 但是如果在@GetMapping 后面的请求地址配置
     * 不会走自定义的sentinel的兜底方法  会用sentinel默认的方法
     *
     */

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handlerException")
    public CommonResult byResource() {
        return   CommonResult.success("按照资源名称限流测试",new Payment(2020L,"serial001"));
    }

    public CommonResult handlerException(BlockException exception) {
        return  CommonResult.error(444,"服务被限流暂不可用");
    }

    //没有写blockHandler 使用sentinel默认自带的方法
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return   CommonResult.success("按照byUrl限流测试",new Payment(2020L,"serial002"));
    }

    //如果出现sentinel的配置错误 会找到CustomerBlockHandler 类下面的handlerException2方法进行处理
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException1")
    public CommonResult customerBlockHandler() {
        return   CommonResult.success("按照客户自定义限流测试",new Payment(2020L,"serial003"));
    }
}
