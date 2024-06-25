package com.laredo.mls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class InjectConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.of("ADMIN");
            }

            try {
                return Optional.ofNullable(authentication.getName());
            } catch (Exception e) {
                return Optional.of("ADMIN");
            }
        };
    }


    @Scheduled(fixedRate = 10000, zone = "America/La_Paz")
    public void scheduled() {
        System.out.println("10 segundos");
    }
    @Scheduled(cron = "0 0 23 * * ?")
    public void scheduled2() {
        System.out.println("scheduled");
    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduled3() {
        System.out.println("Cada minuto");
    }
}
