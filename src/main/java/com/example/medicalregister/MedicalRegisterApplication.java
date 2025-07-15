package com.example.medicalregister;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import jakarta.faces.webapp.FacesServlet;


@SpringBootApplication
public class MedicalRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRegisterApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean<FacesServlet> facesServlet() {
	    ServletRegistrationBean<FacesServlet> servletRegistrationBean =
	        new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
	    servletRegistrationBean.setLoadOnStartup(1);
	    return servletRegistrationBean;
	}
	
}
