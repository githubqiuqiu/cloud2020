package com.wonders.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author qiu
 * @date 2020-07-06 13:47
 */
@Component
@Slf4j
public class MyGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("  pre 自定义过滤器工厂  " + this.getClass().getSimpleName());
        //获取请求参数上的用户名
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");

        if(uname == null) {
            log.info("*****用户名为null");
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        }

        // 在then方法里的，相当于aop中的后置通知
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("  post 自定义过滤器工厂 " + this.getClass().getSimpleName());
        }));


        //return chain.filter(exchange);
    }

    //数字越小 优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
