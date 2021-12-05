package fr.test.taskAppJwt.config;

import fr.test.taskAppJwt.model.entities.Task;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@Configuration
public class RepoConfig {

    public RepoConfig(RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Task.class);
    }

}
