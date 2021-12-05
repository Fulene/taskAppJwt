package fr.test.taskAppJwt.services;

import fr.test.taskAppJwt.model.dao.RoleRepository;
import fr.test.taskAppJwt.model.dao.UserRepository;
import fr.test.taskAppJwt.model.entities.AppRole;
import fr.test.taskAppJwt.model.entities.AppUser;
import fr.test.taskAppJwt.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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

        saveRole(new AppRole(null, "ADMIN"));
        saveRole(new AppRole(null, "USER"));

        addRoleToUser("ADMIN", "admin");
        addRoleToUser("ADMIN", "user");
        addRoleToUser("USER", "user");
    }

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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