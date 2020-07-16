package com.wonders.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiu
 * @date 2020-06-04 17:01
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        //默认是轮询 现在定义成随机
        return new RandomRule(); // 定义为随机
    }

}