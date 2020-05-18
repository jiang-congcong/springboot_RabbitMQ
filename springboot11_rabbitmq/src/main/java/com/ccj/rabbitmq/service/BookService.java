package com.ccj.rabbitmq.service;

import com.ccj.rabbitmq.entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "atguigu.news") //rabbit监听器
    public void receive(Book book){
        System.out.println("收到消息："+book);
    }

    @RabbitListener(queues = "atguigu") //rabbit监听器
    public void receiveMessage(Message message){
        System.out.println("接收消息头;"+message.getBody());
        System.out.println("接受配置信息："+message.getMessageProperties());
        System.out.println("接收消息类型："+message.getClass());

    }

}
