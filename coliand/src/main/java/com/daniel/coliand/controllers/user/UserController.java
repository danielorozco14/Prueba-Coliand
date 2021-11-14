package com.daniel.coliand.controllers.user;

import com.daniel.coliand.models.users.User;
import com.daniel.coliand.services.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{username}")
    public User getUserInfoFromGithub(@PathVariable String username){
        return userService.getUserInfo(username);
    }
}
