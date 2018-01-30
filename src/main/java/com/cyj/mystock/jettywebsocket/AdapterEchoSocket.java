package com.cyj.mystock.jettywebsocket;

import com.cyj.mystock.thread.QueryStockThread;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;


public class AdapterEchoSocket extends WebSocketAdapter {

    @Override
    public void onWebSocketConnect(Session sess)
    {
        super.onWebSocketConnect(sess);
        thread.setAdapterEchoSocket(this);
        System.out.println("Socket Connected: " + sess);
    }

    @Override
    public void onWebSocketText(String message)
    {
        super.onWebSocketText(message);
        try {
            super.getRemote().sendString(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Received TEXT message: " + message);
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode,reason);
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);
    }

    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }
    QueryStockThread thread = QueryStockThread.getInstance();

}
