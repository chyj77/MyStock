package com.cyj.mystock.websocket.listener;

import com.cyj.mystock.cache.ClientCache;
import org.springframework.stereotype.Component;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.websocket.common.Opcode;
import org.tio.websocket.common.WsResponsePacket;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/9/8.
 */

public class WebsocketSendListener {

    public void send(String str) throws Exception {
        ChannelContext channelContext = (ChannelContext) ClientCache.get("client");
        WsResponsePacket wsPacket = new WsResponsePacket();
        wsPacket.setWsOpcode(Opcode.TEXT);
        wsPacket.setBody(str.getBytes("UTF-8"));
        Aio.send(channelContext,wsPacket);
    }
    public void send(byte[] bytes) throws Exception{
        ChannelContext channelContext = (ChannelContext) ClientCache.get("client");
        WsResponsePacket wsPacket = new WsResponsePacket();
        wsPacket.setWsOpcode(Opcode.BINARY);
        wsPacket.setBody(bytes);
        Aio.send(channelContext,wsPacket);
    }
}
