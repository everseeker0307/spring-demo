package com.everseeker;

import com.everseeker.config.ApplicationConfig;
import com.everseeker.config.RootConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final int port = 8900;
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        ServletHolder holder = new ServletHolder(new ServletContainer(applicationConfig));
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/");
        contextHandler.addServlet(holder, "/*");
        contextHandler.setInitParameter("contextConfigLocation", RootConfig.class.getName());

        Server server = new Server(port);
        server.setHandler(contextHandler);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.warn(e.getStackTrace().toString());
        }
    }
}
