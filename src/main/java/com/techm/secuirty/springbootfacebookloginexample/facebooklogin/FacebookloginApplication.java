package com.techm.security.facebookloginexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
public class FacebookLoginExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacebookLoginExampleApplication.class, args);
	}

}