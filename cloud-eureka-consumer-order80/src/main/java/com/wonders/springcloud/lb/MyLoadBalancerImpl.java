package com.wonders.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiu
 * @date 2020-07-02 18:27
 */
@Component
public class MyLoadBalancerImpl implements MyLoadBalancer{
    private AtomicInteger atomicInteger=new AtomicInteger(0);

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {

        //第几次访问的次数
        int count = getAndIncrement();

        int index=count% serviceInstances.size();

        return serviceInstances.get(index);
    }

    public final int getAndIncrement() {
        int current;
        int next;
        do{
            current=this.atomicInteger.get();
            next = current >=Integer.MAX_VALUE ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));

        System.out.println("****第几次访问，次数next: " + next);
        return  next;
    }
}
