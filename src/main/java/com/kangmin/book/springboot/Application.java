//Main class
package com.kangmin.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Activating JPA Auditing functions
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("Spring Core Version:- " + SpringVersion.getVersion());
        SpringApplication.run(Application.class, args);
    }
}
