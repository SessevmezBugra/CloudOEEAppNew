package com.oee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableFeignClients
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class MaindataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaindataServiceApplication.class, args);
	}
	
	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}
	

}
