package com.nyefan.anaconda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(resourcePattern = "api/*.class")
public class TicTacToeApplication extends SpringBootServletInitializer {
    public static void main(String... args) {
        SpringApplication.run(TicTacToeApplication.class, args);
    }
}
