package com.flameking.ourwechat.session;

import io.netty.channel.Channel;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionMemoryImpl implements Session {

    private final Map<String, Channel> usernameChannelMap = new ConcurrentHashMap<>();
    private final Map<Channel, String> channelUsernameMap = new ConcurrentHashMap<>();
    private final Map<Channel,Map<String,Object>> channelAttributesMap = new ConcurrentHashMap<>();// 每个 channel 包含的属性

    @Override
    public void bind(Channel channel, String weixinId) {
        usernameChannelMap.put(weixinId, channel);
        channelUsernameMap.put(channel, weixinId);
        channelAttributesMap.put(channel, new ConcurrentHashMap<>());
    }

    public Collection<Channel> getAllChannels(){
        return usernameChannelMap.values();
    }

    @Override
    public void unbind(Channel channel) {
        String username = channelUsernameMap.remove(channel);
        usernameChannelMap.remove(username);
        channelAttributesMap.remove(channel);
    }

    @Override
    public Object getAttribute(Channel channel, String name) {
        return channelAttributesMap.get(channel).get(name);
    }

    @Override
    public void setAttribute(Channel channel, String name, Object value) {
        channelAttributesMap.get(channel).put(name, value);
    }

    @Override
    public Channel getChannel(String weixinId) {
        return usernameChannelMap.get(weixinId);
    }

    @Override
    public String toString() {
        return usernameChannelMap.toString();
    }
}
