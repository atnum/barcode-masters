package com.anup;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@WebListener
public class Initializer extends RequestContextListener implements ServletContextInitializer  {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.err.println("------------------------------------");
		servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
		servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "0");
		servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
		servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
		servletContext.setInitParameter("BootsFaces_USETHEME", "true");
		servletContext.setInitParameter("primefaces.FONT_AWESOME", "true");;
		servletContext.setInitParameter("BootsFaces_THEME", "united");
		servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING", "false");
		servletContext.setInitParameter("javax.faces.WEBAPP_RESOURCES_DIRECTORY", "/**");
	}
}