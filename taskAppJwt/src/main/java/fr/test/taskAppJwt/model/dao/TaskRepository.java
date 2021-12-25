package fr.test.taskAppJwt.model.dao;

import fr.test.taskAppJwt.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface TaskRepository extends JpaRepository<Task, Long> {
}
