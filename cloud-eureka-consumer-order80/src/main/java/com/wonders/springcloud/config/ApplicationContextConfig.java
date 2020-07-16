package com.wonders.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author qiu
 * @date 2020-05-08 14:05
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    //restTemplate负载均衡  去掉@LoadBalanced 使用自己实现的轮询算法
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return  new RestTemplate();
    }
}
