package fr.test.taskAppJwt.services;

import fr.test.taskAppJwt.model.dao.RoleRepository;
import fr.test.taskAppJwt.model.dao.UserRepository;
import fr.test.taskAppJwt.model.dto.UserDTO;
import fr.test.taskAppJwt.model.entities.AppRole;
import fr.test.taskAppJwt.model.entities.AppUser;
import fr.test.taskAppJwt.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void mock() {
        saveUser(new AppUser(null, "admin", "admin", new ArrayList<>()));
        saveUser(new AppUser(null, "user", "1234", new ArrayList<>()));
        saveUser(new AppUser(null, "user2", "1234", new ArrayList<>()));

        saveRole(new AppRole(null, "ADMIN"));
        saveRole(new AppRole(null, "USER"));

        addRoleToUser("ADMIN", "admin");
        addRoleToUser("USER", "user");
        addRoleToUser("USER", "user2");
    }

    public void test() {
        List<AppUser> users = userRepository.findByRoleId(2);
        System.out.println(users);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AppUser saveUser(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword()))
            throw new RuntimeException("Error confirm password");

        if (findUserByName(userDTO.getName()) != null)
            throw new RuntimeException("Error username already exist");

        List<AppRole> userRoles = new ArrayList<>();
        userRoles.add(roleRepository.findByName("USER"));
        return saveUser(new AppUser(null, userDTO.getName(), userDTO.getPassword(), userRoles));
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public AppUser findUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void addRoleToUser(String roleName, String userName) {
        findUserByName(userName).getRoles().add(roleRepository.findByName(roleName));
    }

}
