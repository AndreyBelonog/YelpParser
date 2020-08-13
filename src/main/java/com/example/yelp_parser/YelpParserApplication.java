package com.example.yelp_parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YelpParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(YelpParserApplication.class, args);
    }
}
