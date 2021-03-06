package com.example;

import com.example.enums.Events;
import com.example.enums.OrderStatus;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
//@EnableEurekaServer
//@EnableSwagger2 //开启swagger
//@EnableOAuth2Sso
@MapperScan("com.example.service")
public class CloudApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CloudApplication.class, args);
	}

	/**
	 * Spring Boot自定义错误页面，Whitelabel Error Page处理方式
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

			container.addErrorPages(error401Page, error404Page, error500Page);
		});
	}

	@Autowired
	private StateMachine<OrderStatus, Events> stateMachine;

	@Override
	public void run(String... args) throws Exception {
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		stateMachine.sendEvent(Events.RECEIVE);
	}

}
