package com.bigtree.aitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class AiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiTestApplication.class, args);
	}

}
