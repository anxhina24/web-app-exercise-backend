package de.anevis.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Import;
import de.anevis.backend.config.SecurityConfig;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@Import(SecurityConfig.class)
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}

@RestController
class HelloWorldController {
	@GetMapping("/")
	public String hello() {
		return "hello world!";
	}
}
