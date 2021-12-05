package fr.test.taskAppJwt.model.dao;

import fr.test.taskAppJwt.model.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByName(String name);

}
