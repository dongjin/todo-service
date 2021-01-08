package com.dongjin.son.todo.todoservice;

import com.dongjin.son.todo.todoservice.services.RealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TodoServiceApplication extends SpringBootServletInitializer {

	// (3) To Build as a WAR
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TodoServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoServiceApplication.class, args);
	}

}
//public class TodoServiceApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(TodoServiceApplication.class, args);
//	}
//
//}
