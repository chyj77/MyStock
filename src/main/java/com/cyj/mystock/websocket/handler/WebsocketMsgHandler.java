package com.cyj.mystock.websocket.handler;

import com.cyj.mystock.cache.ClientCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequestPacket;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2017/9/8.
 */
public class WebsocketMsgHandler implements IWsMsgHandler {

    private static Logger log = LoggerFactory.getLogger(WebsocketMsgHandler.class);

    public WebsocketMsgHandler() {
    }

    @Override
    public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        try {
            Object obj = request.getHttpSession().getAttribute("test");
        }catch (Exception e){
            e.printStackTrace();
        }
        ClientCache.set("client",channelContext);
        return httpResponse;
    }

    @Override
    public Object onBytes(WsRequestPacket wsRequestPacket, byte[] bytes, ChannelContext channelContext) throws Exception {
        String ss = new String(bytes, "utf-8");
        log.info("收到byte消息:{},{}", bytes, ss);

        //		byte[] bs1 = "收到byte消息".getBytes("utf-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);

        return buffer;
    }

    @Override
    public Object onClose(WsRequestPacket websocketPacket, byte[] bytes, ChannelContext channelContext) throws Exception {
        Aio.remove(channelContext, "receive close flag");
        return null;
    }

    @Override
    public Object onText(WsRequestPacket wsRequestPacket, String text, ChannelContext channelContext) throws Exception {

        return "收到text消息:" + text;
    }
}
