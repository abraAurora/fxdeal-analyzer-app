package com.fxdealanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fxdealanalyzer.repository")
public class BloombergDataFxApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloombergDataFxApplication.class, args);
	}

}
