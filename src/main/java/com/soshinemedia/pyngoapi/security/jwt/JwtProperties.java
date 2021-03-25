package com.soshinemedia.pyngoapi.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

	private String secretKey = "AB54A98D34A3EE0B";

	//validity in milliseconds
	private long validityInMs = 3600000 * 3; // 1h
}
