package com.mvc.springprojections;

import org.springframework.boot.SpringApplication;

public class TestSpringProjectionsApplication {
    public static void main(String[] args) {
        SpringApplication.from(SpringProjectionsApplication::main).with(TestcontainersConfiguration.class).run(args);
    }
}
