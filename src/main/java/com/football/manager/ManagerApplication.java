package com.football.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Rest Api football manager to operate with basic CRUD operations
 * @author Artem Komarov
 */

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class ManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}

}
