package ar.edu.iua.proyectoFinal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class ProyectoFinalApplication implements CommandLineRunner {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}


	@Autowired
	private PasswordEncoder pe;

	@Override
	public void run(String... args) throws Exception {
		log.warn("Inicio de la Aplicacion");
		log.warn("La contraseña 1234 es "+ pe.encode("1234"));

	}

}
