package pe.edu.cibertec.APPDAWIPROYECTO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppDawiProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppDawiProyectoApplication.class, args);

		// Generar una contraseña encriptada
		String password = "123456";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println("Contraseña encriptada: " + hashedPassword);
	}
}
