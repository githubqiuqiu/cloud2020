package com.wonders.springcloud.service.impl;

import com.wonders.springcloud.entity.Payment;
import com.wonders.springcloud.repository.PaymentRepository;
import com.wonders.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qiu
 * @date 2020-05-08 10:23
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public int create(Payment payment) {
        return paymentRepository.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.getPaymentById(id);
    }
}
