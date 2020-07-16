package com.wonders.springcloud.service.impl;

import com.wonders.springcloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * 操作消息中间件
 * @author qiu
 * @date 2020-07-06 16:29
 */
//定义消息的推送管道
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {

    /**
     *
     * 生产者
     * 1.source
     * 2.channel
     * 3.binder
     *
     */

    //消息发送的channel管道
    @Autowired
    private MessageChannel output;



    @Override
    public String send() {
        String uuid= UUID.randomUUID().toString();
        //发送消息到mq
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("发送消息:"+uuid);
        return uuid;
    }
}
