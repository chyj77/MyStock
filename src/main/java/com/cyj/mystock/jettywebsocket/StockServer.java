package com.cyj.mystock.jettywebsocket;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

public class StockServer {

    public static void main(String[] args)
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(80);
        server.addConnector(connector);




        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.setContextPath("/mystock");

        // Add a websocket to a specific path spec
        ServletHolder holderEvents = new ServletHolder("ws-events", EventServlet.class);
//        context.addServlet(holderEvents, "/events/*");
//
//        server.setHandler(context);

        WebAppContext webAppContext = new WebAppContext();
        // 设置描述符位置
        webAppContext.setDescriptor("./src/main/webapp/WEB-INF/web.xml");
        // 设置Web内容上下文路径
        webAppContext.setResourceBase("./src/main/webapp");
        // 设置上下文路径
        webAppContext.setContextPath("/mystock");


        webAppContext.addServlet(holderEvents, "/events/*");
        // This webapp will use jsps and jstl. We need to enable the
        // AnnotationConfiguration in order to correctly
        // set up the jsp container
        Configuration.ClassList classlist = Configuration.ClassList
                .setServerDefault( server );
        classlist.addBefore(
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration" );

        webAppContext.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$" );
        webAppContext.setParentLoaderPriority(true);
        server.setHandler(webAppContext);

        try
        {
            server.start();
            server.dump(System.err);
            server.join();
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }

}
