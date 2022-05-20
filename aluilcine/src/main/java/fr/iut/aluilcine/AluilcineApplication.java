package fr.iut.aluilcine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluilcineApplication {

	static Logger logger = LoggerFactory.getLogger(AluilcineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AluilcineApplication.class, args);
		logger.info(String.format("%s started", AluilcineApplication.class.getSimpleName()));
	}

}
