package com.elciocestari.repositories;

import com.elciocestari.entities.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {
    //TODO - replace in the future to be only one User.
    public List<User> findByUsername(String username) {
        return find("{'username': ?1}", username).list();
    }
}
