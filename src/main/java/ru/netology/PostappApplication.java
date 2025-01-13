package ru.netology;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.netology.config.WebConfig;

public class PostappApplication {

	public static void main(String[] args) throws LifecycleException {
		// Создаём Spring-контекст
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class);
		context.refresh();

		// Создаём DispatcherServlet с контекстом
		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

		// Настраиваем Embedded Tomcat
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8082);
		tomcat.getConnector(); // Инициализируем коннектор

		// Создаём контекст приложения
		var tomcatContext = tomcat.addContext("", null);

		// Добавляем DispatcherServlet
		Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet).setLoadOnStartup(1);
		tomcatContext.addServletMappingDecoded("/", "dispatcher");

		// Запускаем Tomcat
		tomcat.start();
		tomcat.getServer().await();
	}
}


