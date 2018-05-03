package com.estore.service.impl;

import com.estore.service.MQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class MQServiceImpl implements MQService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private Logger logger = LoggerFactory.getLogger(MQServiceImpl.class);

    @Override
    public void sendMessage(Object message) throws IOException, TimeoutException {

        //发送消息到消息队列服务器中，并得到回馈内容
         amqpTemplate.convertAndSend("queueTest", message); /*message1 -> {
            MessageProperties props = message1.getMessageProperties();
            //把版本加入消息头中
            props.setHeader("header", "1.0.0");
            logger.debug("设置RPC消息的TTL为{}", 30000);
            props.setExpiration(String.valueOf(30000));
            logger.debug("sendMessage.发送的信息为"+message);
            return message1;
        });*/
        logger.debug("sendMessage.发送的信息为"+message);
    }
}
