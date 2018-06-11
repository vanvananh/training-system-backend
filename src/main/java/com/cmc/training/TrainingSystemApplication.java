package com.cmc.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @EnableResourceServer
@EnableAutoConfiguration
@ComponentScan
public class TrainingSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(TrainingSystemApplication.class, args);
  }

}
