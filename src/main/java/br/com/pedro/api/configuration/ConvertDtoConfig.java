package br.com.pedro.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.pedro.api.dto.ConvertDto;


@Configuration
public class ConvertDtoConfig {
	
	@Bean
	public ConvertDto convertDto() {
		return new ConvertDto();
	}

}
