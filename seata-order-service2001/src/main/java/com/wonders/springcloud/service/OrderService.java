package com.wonders.springcloud.service;

import com.wonders.springcloud.domain.Order;

/**
 * @author qiu
 * @date 2020-07-16 13:52
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
