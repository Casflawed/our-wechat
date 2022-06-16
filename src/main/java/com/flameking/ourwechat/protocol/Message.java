package com.flameking.ourwechat.protocol;

import lombok.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Message implements Serializable {
    public static final int LoginRequestMessage = 0;
    public static final int LoginResponseMessage = 1;
    public static final int GroupChatRequestMessage = 10;
    public static final int GroupChatResponseMessage = 11;

    private static final Map<Integer, Class<?>> messageClasses = new HashMap<>();

    private int sequenceId;
    private int messageType;

    static {
        messageClasses.put(LoginRequestMessage, LoginRequestMessage.class);
        messageClasses.put(LoginResponseMessage, LoginResponseMessage.class);
        messageClasses.put(GroupChatRequestMessage, GroupChatRequestMessage.class);
        messageClasses.put(GroupChatResponseMessage, GroupChatResponseMessage.class);
    }


    public static Class<?> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }

    public abstract int getMessageType();
}
