package com.education;

import com.education.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class) //добавили конфигурацию Spring Security
public class SpringBasicApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBasicApplication.class, args);
	}
}
