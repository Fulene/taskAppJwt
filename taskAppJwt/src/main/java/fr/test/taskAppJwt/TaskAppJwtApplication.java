package fr.test.taskAppJwt;

import fr.test.taskAppJwt.services.TaskService;
import fr.test.taskAppJwt.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication()
public class TaskAppJwtApplication implements CommandLineRunner {

	@Autowired
	private TaskService taskService;

	@Autowired
	private IAccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(TaskAppJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		taskService.mock();
		accountService.mock();
		accountService.test();
	}

// ========== Autre méthode permettant de configurer la CORS policy ==========
	@Bean
	public WebMvcConfigurer configureCors() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("http://localhost:4200");
			}

		};
	}
}
