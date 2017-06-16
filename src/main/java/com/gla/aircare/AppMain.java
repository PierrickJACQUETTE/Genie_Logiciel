package com.gla.aircare;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.gla.aircare.dao.AddDao;
import com.gla.aircare.dao.AddFlight;
import com.gla.aircare.dao.Dao;
import com.gla.aircare.tools.SendMail;

public class AppMain {

	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public static void main(String[] args) throws Exception {
		// Initialize the server
		Server server = new Server();

		// Add a connector
		ServerConnector connector = new ServerConnector(server);
		connector.setHost("0.0.0.0");
		// connector.setPort(8080);
		connector.setPort(2626);
		connector.setIdleTimeout(30000);
		server.addConnector(connector);

		// Configure Jersey
		ResourceConfig rc = new ResourceConfig();
		rc.packages(true, "com.gla.aircare.ws");
		rc.register(JacksonFeature.class);
		rc.register(LoggingFilter.class);

		// Add a servlet handler for web services (/ws/*)
		ServletHolder servletHolder = new ServletHolder(new ServletContainer(rc));
		ServletContextHandler handlerWebServices = new ServletContextHandler(ServletContextHandler.SESSIONS);
		handlerWebServices.setContextPath("/ws");
		handlerWebServices.addServlet(servletHolder, "/*");

		// Add a handler for resources (/*)
		ResourceHandler handlerPortal = new ResourceHandler();
		handlerPortal.setResourceBase("src/main/webapp/HTML");
		handlerPortal.setDirectoriesListed(false);
		handlerPortal.setWelcomeFiles(new String[] { "login.html" });
		ContextHandler handlerPortalCtx = new ContextHandler();
		handlerPortalCtx.setContextPath("/");
		handlerPortalCtx.setHandler(handlerPortal);

		// Activate handlers
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		contexts.setHandlers(new Handler[] { handlerWebServices, handlerPortalCtx });
		server.setHandler(contexts);

		// Start server
		server.start();
		new AddFlight().addNewFlight();;
		new AddDao();

		updateStatusForTwoMinute();
		sendMailForOneDays();

	}

	public static void updateStatusForTwoMinute() {
		final Runnable beeper = new Runnable() {
		
			public void run() {
				Dao.getPlaneDao().updatePlane();
			}
		};
		scheduler.scheduleAtFixedRate(beeper, 0, 2, TimeUnit.MINUTES);
	}

	public static void sendMailForOneDays() {
		final Runnable beeper = new Runnable() {
			
			public void run() {
				SendMail sm = new SendMail();
				sm.sendMailMro();
			}
		};
		scheduler.scheduleAtFixedRate(beeper, 0, 24, TimeUnit.DAYS);
	}

}
