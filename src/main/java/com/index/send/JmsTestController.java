package com.index.send;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("jms")
public class JmsTestController {
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;
    
    @Resource
    private JmsTemplate jmsTemplate;
    
    public void sendMessage(Destination destination, final String message) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    } 

    @RequestMapping("test")
    public @ResponseBody String testSend() throws Exception {
        
        sendMessage(destination, "{'title':'name'}");
        
        return "jms exute complete";
    }
}