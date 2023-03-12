package com.elciocestari.services;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
import com.elciocestari.mappers.UserMapper;
import com.elciocestari.repositories.UserRepository;
import org.jboss.resteasy.reactive.common.NotImplementedYet;

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

    public Void delete(String username) {
        repository.findByUsername(username)
                .forEach(repository::delete);
        return null;
    }

    public UserResponseDTO update(String username, UserRequestDTO dto) {
        throw new NotImplementedYet();
    }
}
