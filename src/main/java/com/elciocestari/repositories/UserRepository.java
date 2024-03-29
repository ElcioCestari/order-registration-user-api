package com.elciocestari.repositories;

import com.elciocestari.entities.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {

    public Optional<User> findByUsername(String username) {
        List<User> list = find("{'username': ?1}", username).list();
        if (list.size() > 1) {
            throw new RuntimeException("More then one row allowed returned");
        }
        return ofNullable(list.isEmpty() ? null : list.get(0));
    }
}
