package com.wonders.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiu
 * @date 2020-07-16 13:55
 */
@Configuration
@MapperScan({"com.wonders.springcloud.repository"})
public class MyBatisConfig {


}
