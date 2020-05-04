package com.oee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

//BBBBBBBB
@SpringBootApplication
public class ConfirmationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfirmationServiceApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}
}
