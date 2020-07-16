package com.wonders.springcloud.service.impl;

import com.wonders.springcloud.repository.AccountRepository;
import com.wonders.springcloud.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author qiu
 * @date 2020-07-16 14:49
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("----> account-service中扣减用户余额开始");

        //模拟异常 测试seata的回滚
        //int i=10/0;
        accountRepository.decrease(userId,money);
        LOGGER.info("----> account-service中扣减用户余额开始");
    }
}
