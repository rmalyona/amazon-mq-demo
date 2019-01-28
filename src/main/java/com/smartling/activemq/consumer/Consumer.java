package com.smartling.activemq.consumer;

import com.smartling.activemq.pto.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Profile("consumer")
public class Consumer
{
    @Value("${application.delivery.check:false}")
    private boolean check;

    private long counter;

    @JmsListener(destination = "${application.queue.name}", containerFactory = "myFactory")
    public void receiveMessage(Message message) {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            System.err.println("Exception " + e);
        }
        System.out.println("Received <" + message + ">");
        if (check)
        {
            if (counter == 0)
            {
                counter = message.getCounter();
            }
            else
            {
                counter++;
                if (counter != message.getCounter())
                {
                    System.err.println("Integrity check failed. \n" +
                            "Internal counter doesn't match received: " + counter + " != " + message.getCounter());
                    counter = message.getCounter();
                }
            }
        }
    }

}
