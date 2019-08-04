package com.jumkid.oauthservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@SpringBootApplication
public class OauthServiceApplication extends SpringBootServletInitializer {

	static Logger logger = LoggerFactory.getLogger(OauthServiceApplication.class);

	public static void main(String[] args) {
		logger.debug("oauth service starts");

		SpringApplication.run(OauthServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

}
