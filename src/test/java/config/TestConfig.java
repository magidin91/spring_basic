package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.education.jpa"})
@ComponentScan(basePackages = {"com.education.service"})
@EntityScan(basePackages = {"com.education.entity"})
public class TestConfig {
}
