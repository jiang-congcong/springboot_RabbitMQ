package com.ccj.rabbitmq;

import com.ccj.rabbitmq.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));//创建exchange
        System.out.println("创建完成");
    }

    @Test
    public void createQueue(){
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));//创建queue
        System.out.println("创建完成");
    }

    @Test
    public void createBinding(){
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.binding",null));//添加exchange和queue的绑定
        System.out.println("绑定完成");
    }


    /**
     * 1.单播  direct
     */

    @Test
    public void contextLoads() {
        //rabbitTemplate.send();
        String exchange = "exchange.direct";
        String routingKey = "atguigu.news";
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("helloworld","123","true"));
        Book book = new Book("三国演义","吴冠中");
        rabbitTemplate.convertAndSend(exchange,routingKey,book);
        //rabbitTemplate.convertAndSend(exchange,routingKey,map);//对象自动序列化发给rabbitmq
    }

    @Test
    public void receive(){
        String queueName = "atguigu.news";
        Object object = rabbitTemplate.receiveAndConvert(queueName);//接收数据
        System.out.println("接收类型："+object.getClass());
        System.out.println("打印结果："+object);
    }

    /**
     * 广播   fanout
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
    }


}
