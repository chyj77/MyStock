package com.cyj.mystock.jettywebsocket;

import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;

@SuppressWarnings("serial")
public class EventServlet extends WebSocketServlet{

    @Override
    public void configure(WebSocketServletFactory factory)
    {
        factory.register(AdapterEchoSocket.class);
    }
}
