package com.example.DBS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DbsPdfViewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbsPdfViewerApplication.class, args);
	}

}
