package com.daniel.coliand.services.user;

import com.daniel.coliand.models.users.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate rst;


    public UserService(RestTemplateBuilder rstBlder) {
        this.rst = rstBlder.build();
    }

    public User getUserInfo(String username){
        String url = String.format("https://api.github.com/users/%s", username );
        return this.rst.getForObject(url, User.class);
    }

}
