package com.wonders.springcloud.service.impl;

import com.wonders.springcloud.repository.StorageRepository;
import com.wonders.springcloud.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qiu
 * @date 2020-07-16 14:30
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Autowired
    private StorageRepository storageRepository;

    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("----> storage-service中扣减库存开始");
        storageRepository.decrease(productId,count);
        LOGGER.info("----> storage-service中扣减库存结束");
    }
}
