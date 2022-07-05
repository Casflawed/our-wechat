package com.flameking.ourwechat.protocol;

import lombok.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Message implements Serializable {
//    public static final int LoginRequestMessage = 0;
//    public static final int LoginResponseMessage = 1;
    public static final int RegisterChannelIdMessage = 2;
    public static final int RegisterChannelIdResponseMessage = 3;
    public static final int GroupChatMessage = 4;

//    public static final int GroupChatRequestMessage = 10;
//    public static final int GroupChatResponseMessage = 11;

    private static final Map<Integer, Class<?>> messageClasses = new HashMap<>();

//    private int sequenceId;
    private int messageType;

    public Message(int messageType) {
        this.messageType = messageType;
    }

    static {
        messageClasses.put(RegisterChannelIdMessage, RegisterChannelIdMessage.class);
        messageClasses.put(RegisterChannelIdResponseMessage, RegisterChannelIdMessage.class);
        messageClasses.put(GroupChatMessage, GroupChatMessage.class);




    }


    public static Class<?> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }

    public abstract int getMessageType();
}
