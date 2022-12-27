package com.example.persistence.demo;

import com.example.persistence.demo.domain.BasicEntity;
import com.example.persistence.demo.repositories.BasicEntityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BasicSpringDataDemo {

	public static void main(String[] args) {
//		final var applicationContext = SpringApplication.run(BasicSpringDataDemo.class, args);
		final var applicationContext = new AnnotationConfigApplicationContext(RepositoryConfiguration.class);
		final BasicEntityRepository basicEntityRepository = applicationContext.getBean(BasicEntityRepository.class);
		BasicEntity basicEntity = new BasicEntity();
		basicEntity.setData("data");
		basicEntityRepository.save(basicEntity);
		applicationContext.close();
	}

}
