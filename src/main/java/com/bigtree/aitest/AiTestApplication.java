package com.bigtree.aitest;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
@EnableProcessApplication
@MapperScan(value="com.bigtree.aitest.mapper")
public class AiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiTestApplication.class, args);
	}

}
