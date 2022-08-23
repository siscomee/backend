package br.com.senac.siscomee;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import ch.qos.logback.classic.Logger;

@Configuration
@EnableScheduling
@SpringBootApplication
@EnableJpaAuditing
public class SiscomeeApp {

	public static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SiscomeeApp.class);
     
	public static void main(String[] args) throws Exception {

		LOGGER.info("Iniciando API SISCOMEE");
		SpringApplication.run(SiscomeeApp.class, args);
		LOGGER.info("API SISCOMEE inicializada com sucesso.");
	}
}
