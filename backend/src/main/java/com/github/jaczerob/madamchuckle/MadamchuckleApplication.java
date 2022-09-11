package com.github.jaczerob.madamchuckle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class MadamchuckleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadamchuckleApplication.class, args);
	}

}
