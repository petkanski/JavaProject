package com.devision.javaProject;

import com.devision.javaProject.models.Group;
import com.devision.javaProject.models.User;
import com.devision.javaProject.models.Plot;
import com.devision.javaProject.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class JavaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}


	@Bean
	public CommandLineRunner setupDefaultUser(UserService service) {
		return args -> {
			service.save(new User(
					"Plamen",
					"plamen@abv.bg",
					20,
					"admin", //username
					"admin", //password
					Arrays.asList(new Group("ADMIN")),//roles
					Arrays.asList(new Plot("Name", 12353213.231, "Corn")),
					true//Active
			));
			service.save(new User(
					"Ivan",
					"ivan@abv.bg",
					20,
					"user", //username
					"user", //password
					Arrays.asList(new Group("USER")),//roles
					Arrays.asList(new Plot("Name1", 12555213.231, "Wheat")),
					true//Active
			));
		};
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {

		return new RepositoryRestConfigurerAdapter() {

			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.setRepositoryDetectionStrategy(
						RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
			}
		};
	}
}
