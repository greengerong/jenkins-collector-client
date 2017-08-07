package com.github.greengerong;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class JenkinsCollectorClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JenkinsCollectorClientApplication.class, args);
    }
}
