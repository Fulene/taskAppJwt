package fr.test.taskAppJwt.services.interfaces;

import fr.test.taskAppJwt.model.dto.UserDTO;
import fr.test.taskAppJwt.model.entities.AppRole;
import fr.test.taskAppJwt.model.entities.AppUser;

public interface IAccountService {

    void mock();
    AppUser saveUser(AppUser user);
    AppUser saveUser(UserDTO userDTO);
    AppRole saveRole(AppRole role);
    AppUser findUserByName(String name);
    void addRoleToUser(String roleName, String userName);

}
