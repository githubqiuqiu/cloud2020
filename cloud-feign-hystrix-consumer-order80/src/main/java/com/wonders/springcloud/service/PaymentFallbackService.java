package com.wonders.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author qiu
 * @date 2020-07-03 15:49
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String ok(Integer id) {
        return "PaymentFallbackService降级处理ok方法";
    }

    @Override
    public String timeout(Integer id) {
        return "PaymentFallbackService降级处理timeout方法";
    }
}
