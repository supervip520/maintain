package com.along.configure;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


@Configuration
public class SessionConfiguration  implements WebMvcConfigurer {

	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/upload/");

		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Bean
    public SimpleMappingExceptionResolver resolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/403");
        resolver.setExceptionMappings(properties);
        return resolver;
    }
	

}
