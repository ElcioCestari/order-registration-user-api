package com.elciocestari.services;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
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

    public UserResponseDTO save(UserRequestDTO dto) {
        User user = new User();
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        repository.persist(user);
        return new UserResponseDTO(user.getUsername());
    }
}
