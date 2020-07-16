package com.wonders.springcloud.service;

import com.wonders.springcloud.entity.Payment;

/**
 * @author qiu
 * @date 2020-05-08 10:22
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
