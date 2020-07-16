package com.wonders.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wonders.springcloud.common.entity.CommonResult;
import com.wonders.springcloud.entity.Payment;


/**
 * 自定义处理sentinel错误的类
 * @author qiu
 * @date 2020-07-08 17:29
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException1(BlockException exception) {
        return  CommonResult.error(444,"按照客户自定义的Glogal 全局异常处理 ---- 1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return  CommonResult.error(444,"按照客户自定义的Glogal 全局异常处理 ---- 2");
    }
}
