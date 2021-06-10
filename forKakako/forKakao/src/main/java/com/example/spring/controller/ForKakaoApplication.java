package com.example.spring.controller;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.example.spring"})
@EnableJpaRepositories(basePackages = {"com.example.spring"})
@SpringBootApplication(scanBasePackages ={"com.example.spring"})
public class ForKakaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForKakaoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		return modelMapper;
	}

}
