package dev.jaczerob.eras.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ErasApplication {

    public static void main(final String... args) {
        SpringApplication.run(ErasApplication.class, args);
    }

}
