package com.laredo.mls;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EntityScan("com.laredo")
@EnableJpaRepositories(basePackages = {"com.laredo.repository"})
@ComponentScan(basePackages = {"com.laredo"})
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableJpaAuditing
@EnableScheduling
@EnableAsync
public class MlsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MlsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
