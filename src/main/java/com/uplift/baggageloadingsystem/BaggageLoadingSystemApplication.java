package com.uplift.baggageloadingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
		basePackageClasses = { BaggageLoadingSystemApplication.class, Jsr310JpaConverters.class }
)
@SpringBootApplication
public class BaggageLoadingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaggageLoadingSystemApplication.class, args);
	}
}
