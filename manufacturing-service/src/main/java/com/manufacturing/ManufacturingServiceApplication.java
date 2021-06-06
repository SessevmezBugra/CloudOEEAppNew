package com.manufacturing;

import brave.sampler.Sampler;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class ManufacturingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManufacturingServiceApplication.class, args);
	}
	
	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper mapper =new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}

}
