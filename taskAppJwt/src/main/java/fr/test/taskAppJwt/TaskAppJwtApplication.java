package fr.test.taskAppJwt;

import fr.test.taskAppJwt.services.TaskService;
import fr.test.taskAppJwt.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
		taskService.mockTasks();
		accountService.mock();
	}
}
