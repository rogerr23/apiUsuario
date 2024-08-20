package br.com.cotiinformatica.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cotiinformatica.filters.JwtBearerFilter;

@Configuration
public class JwtBearerConfig {

	// Este metodo devera configurar o filtro do JWT (JwtBearerFilter) e definir
	// para quais endpoints da API este filtro sera aplicado.
	@Bean
	FilterRegistrationBean<JwtBearerFilter> jwtFilter() {

		FilterRegistrationBean<JwtBearerFilter> filter = new FilterRegistrationBean<JwtBearerFilter>();
		filter.setFilter(new JwtBearerFilter());

		filter.addUrlPatterns("/api/usuarios/obter-dados");
		return filter;

	}

}
