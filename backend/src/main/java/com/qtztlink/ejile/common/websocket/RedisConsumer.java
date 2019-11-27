package com.qtztlink.ejile.common.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtztlink.ejile.system.model.communication.MessageContact;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.IOException;

/**
 * @author Tiger
 * @date 2019-11-25
 * @see com.qtztlink.ejile.common.websocket
 **/
public class RedisConsumer implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] bytes1 = message.getBody();
        String encoded = new String(bytes1).split(",", 2)[1];
        ObjectMapper om = new ObjectMapper();
        try {
            MessageContact messageEntity = om.readValue(encoded, MessageContact.class);
            MyWebSocket.redisSendToUser(messageEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
