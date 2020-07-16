package com.wonders.springcloud;

import com.wonders.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author qiu
 * @date 2020-05-08 13:52
 */

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
//替换ribbon自带的轮询算法 表示访问 CLOUD-PROVIDER-PAYMENT 这个服务 使用自定义的规则MySelfRule定义的规则
//@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT",configuration = MySelfRule.class)
public class OrderEurekaMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderEurekaMain80.class,args);
    }
}