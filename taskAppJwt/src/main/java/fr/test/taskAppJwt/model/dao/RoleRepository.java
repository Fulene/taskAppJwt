package fr.test.taskAppJwt.model.dao;

import fr.test.taskAppJwt.model.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {

    AppRole findByName(String name);

}
