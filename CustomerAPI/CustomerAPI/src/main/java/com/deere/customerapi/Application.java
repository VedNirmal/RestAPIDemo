package com.deere.customerapi;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.deere.customerapi.interceptor.CustomerAPIInterceptor;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application extends WebMvcConfigurerAdapter  implements WebApplicationInitializer  {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

      
    @Bean
    public CustomerAPIInterceptor customerAPIInterceptor() {
        return new CustomerAPIInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(customerAPIInterceptor()).addPathPatterns("/**");
	}


	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	}
}
