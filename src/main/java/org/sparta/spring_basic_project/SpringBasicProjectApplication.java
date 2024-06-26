package org.sparta.spring_basic_project;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@Generated
public class SpringBasicProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBasicProjectApplication.class, args);
    }

}
