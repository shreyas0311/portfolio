package com.portfolio.my_portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.portfolio.my_portfolio")
public class MyPortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPortfolioApplication.class, args);
	}

}
