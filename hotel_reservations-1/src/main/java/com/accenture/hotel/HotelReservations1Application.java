package com.accenture.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class HotelReservations1Application {

	public static void main(String[] args) {
		SpringApplication.run(HotelReservations1Application.class, args);
	}

}
