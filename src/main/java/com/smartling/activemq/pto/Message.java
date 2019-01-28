package com.smartling.activemq.pto;

public class Message
{
    private String body;
    private long counter;

    public Message()
    {
    }

    public Message(String body, long counter) {
        this.body = body;
        this.counter = counter;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public long getCounter()
    {
        return counter;
    }

    public void setCounter(long counter)
    {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return String.format("Message{body=%s,counter=%d}", getBody(), getCounter());
    }

}
