package com.example.simple;

import com.example.simple.config.HelloSelector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class SimpleApplication {


	public static void main(String[] args) {

		SpringApplication.run(SimpleApplication.class, args);
	}

}
