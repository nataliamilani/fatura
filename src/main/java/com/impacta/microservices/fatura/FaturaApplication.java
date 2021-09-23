package com.impacta.microservices.fatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.impacta.microservices.fatura"})
public class FaturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaturaApplication.class, args);
	}

}
