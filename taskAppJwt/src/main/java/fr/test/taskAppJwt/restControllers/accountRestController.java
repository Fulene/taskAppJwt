package fr.test.taskAppJwt.restControllers;

import fr.test.taskAppJwt.model.dto.UserDTO;
import fr.test.taskAppJwt.model.entities.AppUser;
import fr.test.taskAppJwt.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class accountRestController {

    @Autowired
    private IAccountService accountService;

    @ResponseBody
    @PostMapping("/register")
    public AppUser register(@RequestBody UserDTO userDTO) {
        return accountService.saveUser(userDTO);
    }

}
