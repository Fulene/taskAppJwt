package fr.test.taskAppJwt.security.services;

import fr.test.taskAppJwt.model.entities.AppUser;
import fr.test.taskAppJwt.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = accountService.findUserByName(username);

        if (user == null) throw new UsernameNotFoundException("Bad credentials");

        System.out.println("2 ========================");
        System.out.println(user);
        System.out.println("2 ========================");

        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());

        return new User(user.getName(), user.getPassword(), authorities);
    }

}
