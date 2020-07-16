package com.wonders.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiu
 * @date 2020-07-06 17:06
 */
@Component
@EnableBinding(Sink.class)
public class ReceiverMessageController {
    /**
     *
     * 消费者
     * 1.sink
     * 2.channel
     * 3.binder
     *
     */


    @Value("${server.port}")
    private String serverPort;


    @StreamListener(Sink.INPUT)
    public void receiver(Message<String> message){

        String msg=message.getPayload();

        String returnMsg="serverPort:"+serverPort+"  收到消息:"+msg;
        System.out.println(returnMsg);
    }

}
