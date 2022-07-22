package com.ibm.academia.restapi.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RestProductosTApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(RestProductosTApplication.class, args);
	}

}
