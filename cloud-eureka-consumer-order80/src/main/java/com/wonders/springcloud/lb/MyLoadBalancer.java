package com.wonders.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author qiu
 * @date 2020-07-02 18:42
 */
public interface MyLoadBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
