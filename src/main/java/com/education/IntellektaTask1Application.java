package com.education;

import com.education.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)
public class IntellektaTask1Application {
	public static void main(String[] args) {
		SpringApplication.run(IntellektaTask1Application.class, args);
	}
}
