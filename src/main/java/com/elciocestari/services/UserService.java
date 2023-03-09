package com.elciocestari.services;

import com.elciocestari.entities.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserService {

    public List<User> findAll() {
        return List.of(new User("username_fake_1", "password_fake_1"),
                new User("username_fake_2", "password_fake_2"));
    }
}
