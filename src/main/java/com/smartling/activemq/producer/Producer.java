package com.smartling.activemq.producer;


import com.smartling.activemq.pto.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("producer")
public class Producer
{
    @Value("${application.queue.name}")
    private String queueName;

    @Value("${application.producer.partitions}")
    private int partitions;

    @Value("${application.total.messages}")
    private int totalMessages;

    @Value("${application.delivery.check}")
    private boolean check;

    private final JmsTemplate jmsTemplate;

    public Producer(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

    @PostConstruct
    public void produce() throws InterruptedException
    {
        for (int i = 0; check || i < totalMessages; i++)
        {
            String partitionKey = "" + i % partitions;
            String body = "test " + partitionKey;
            System.out.println("Sending message " + body);
            jmsTemplate.convertAndSend(queueName, new Message(body, i), message ->
            {
                message.setStringProperty("JMSXGroupID", partitionKey);
                return message;
            });
            Thread.sleep(1000);
        }

    }
}
