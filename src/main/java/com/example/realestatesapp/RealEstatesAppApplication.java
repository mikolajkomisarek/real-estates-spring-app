package com.example.realestatesapp;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RealEstatesAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(RealEstatesAppApplication.class, args);
    }
}
