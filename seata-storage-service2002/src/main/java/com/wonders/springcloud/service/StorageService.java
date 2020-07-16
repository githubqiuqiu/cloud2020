package com.wonders.springcloud.service;

/**
 * @author qiu
 * @date 2020-07-16 14:30
 */
public interface StorageService {

    void decrease(Long productId, Integer count);

}

