package com.flashwizserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.flashwizserver"})
public class FlashWizServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashWizServerApplication.class, args);
	}

}
