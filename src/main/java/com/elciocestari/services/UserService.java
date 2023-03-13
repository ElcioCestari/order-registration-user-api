package com.elciocestari.services;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
import com.elciocestari.dtos.UserUpdateRequestDTO;
import com.elciocestari.mappers.UserMapper;
import com.elciocestari.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
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
        repository.findByUsername(dto.getUsername())
                .ifPresent(user -> {
                    throw new RuntimeException("user already exists");
                });
        var user = mapper.map(dto);
        repository.persist(user);
        return mapper.map(user);
    }

    public Void delete(String username) {
        repository.findByUsername(username)
                .ifPresentOrElse(repository::delete, () -> {
                    throw new NotFoundException();
                });
        return null;
    }

    public UserResponseDTO update(String username, UserUpdateRequestDTO dto) {
        return repository.findByUsername(username)
                .map(user -> mapper.merge(dto, user))
                .map(user -> mapper.map(user))
                .orElseThrow(NotFoundException::new);
    }
}
