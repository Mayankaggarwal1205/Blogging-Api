package com.example.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
//		System.out.println(this.passwordEncoder.encode("1234"));
//		
//		
//		String encodedPassword = "$2a$10$N9AZK0EyQif3RBBX5yumx.nO.dmJYfP6LVzqbG0PiHD/Co6dQPJd6";
//        String rawPassword = "1234";
//
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
//
//        if (matches) {
//            System.out.println("Password matches!");
//        } else {
//            System.out.println("Password does not match.");
//        }
		
	}
}
