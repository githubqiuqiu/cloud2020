package com.wonders.springcloud.service;

import java.math.BigDecimal;

/**
 * @author qiu
 * @date 2020-07-16 14:48
 */
public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
