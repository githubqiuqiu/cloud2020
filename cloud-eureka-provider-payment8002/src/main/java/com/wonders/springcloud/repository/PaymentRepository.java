package com.wonders.springcloud.repository;

import com.wonders.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author qiu
 * @date 2020-05-08 10:10
 */
@Mapper
@Repository
public interface PaymentRepository {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
