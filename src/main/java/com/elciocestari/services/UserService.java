package com.elciocestari.services;

import com.elciocestari.entities.User;
import com.elciocestari.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    public List<User> findAll() {
        return repository.listAll();
    }
}
