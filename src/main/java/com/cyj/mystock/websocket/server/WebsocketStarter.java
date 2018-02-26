package com.cyj.mystock.websocket.server;

import com.cyj.mystock.websocket.handler.WebsocketMsgHandler;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.tio.websocket.server.WsServerStarter;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/8.
 */
@Component("websocketStarter")
public class WebsocketStarter  implements ApplicationListener<ContextRefreshedEvent>
 {
    /**
     * @param args
     * @author tanyaowu
     * @throws IOException
     */
//    public static void main(String[] args) throws IOException {
//        WebsocketStarter appStarter = new WebsocketStarter();
//        appStarter.start(9567, new WebsocketMsgHandler());
//    }

    WsServerStarter wsServerStarter;
    WebsocketMsgHandler websocketMsgHandler;

    /**
     *
     * @author tanyaowu
     */
    public WebsocketStarter() {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("------------wsServerStarter is start ------------------");
        if(contextRefreshedEvent.getApplicationContext().getParent() == null) {
            System.out.println("------------wsServerStarter is start twice ------------------");
            wsServerStarter = new WsServerStarter();
            websocketMsgHandler = new WebsocketMsgHandler();
            try {
                wsServerStarter.start(9321, websocketMsgHandler);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
