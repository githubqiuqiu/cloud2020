package com.wonders.springcloud.service;

import com.wonders.springcloud.common.entity.CommonResult;
import com.wonders.springcloud.entity.Payment;
import org.springframework.stereotype.Component;

/**
 *
 * @author qiu
 * @date 2020-07-09 10:19
 *
 */
@Component
public class PaymentFallbackService implements PaymentService{

    @Override
    public CommonResult paymentSQL(Long id) {
        return CommonResult.error(444,"服务降级返回，---PaymentFallbackService");
    }
}
