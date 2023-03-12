package com.elciocestari.services;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
import com.elciocestari.entities.User;
import com.elciocestari.mappers.UserMapper;
import com.elciocestari.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserMapper mapper;
    @Inject
    UserRepository repository;

    public List<UserResponseDTO> findAll() {
        List<User> users = repository.listAll();
        return mapper.map(users);
    }

    public UserResponseDTO save(UserRequestDTO dto) {
        User user = mapper.map(dto);
        repository.persist(user);
        return new UserResponseDTO(user.getUsername());
    }
}
