package fr.test.taskAppJwt.model.dao;

import fr.test.taskAppJwt.model.entities.AppRole;
import fr.test.taskAppJwt.model.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByName(String name);

    @Query("select u from AppUser u join u.roles r where r.id = ?1")
    List<AppUser> findByRoleId(long id);

}
