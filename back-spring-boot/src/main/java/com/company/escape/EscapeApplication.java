package com.company.escape;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EscapeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EscapeApplication.class, args);
    }

}
