package com.example.medicalregister.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .anyRequest().authenticated()
	        )
	        .with(new OAuth2LoginConfigurer<>(), oauth2Login -> 
		        oauth2Login
			        .defaultSuccessUrl("/medical-register.xhtml", true)
			        .failureHandler((request, response, exception) -> {
			            exception.printStackTrace();
			            response.sendRedirect("/login?error=" + exception.getMessage());
			        })
	        );

		return http.build();
    }
	
}
