package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
		// build.gradle의 spring-boot-starter-web 라이브러리를 통해
		// 스프링 부트는 내장 톰켓 서버를 확용해서 웹서버와 스프링을 함께 실행
	}




}
