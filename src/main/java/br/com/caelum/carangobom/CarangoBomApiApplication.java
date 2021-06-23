package br.com.caelum.carangobom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableSpringDataWebSupport
@EnableCaching
public class CarangoBomApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarangoBomApiApplication.class, args);
	}

}
