package com.elciocestari.services;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
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
        return mapper.map(repository.listAll());
    }

    public UserResponseDTO save(UserRequestDTO dto) {
        var user = mapper.map(dto);
        repository.persist(user);
        return mapper.map(user);
    }
}
