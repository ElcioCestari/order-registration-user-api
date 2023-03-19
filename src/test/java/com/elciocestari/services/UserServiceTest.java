package com.elciocestari.services;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
import com.elciocestari.dtos.UserUpdateRequestDTO;
import com.elciocestari.entities.User;
import com.elciocestari.repositories.UserRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
class UserServiceTest {

    @InjectSpy
    UserRepository repository;

    @Inject
    UserService service;

    @Test
    void findAll() {
        List<UserResponseDTO> all = service.findAll();
        assertNotNull(all);
    }

    @Test
    void save() {
        UserResponseDTO dto = service.save(new UserRequestDTO("elcio", "elcio_password"));
        assertNotNull(dto);
    }

    @Test
    void delete() {
        service.delete("elcio");
        verify(repository, times(1)).delete(any(User.class));
    }

    @Test
    void update() {
        service.update("elcio", new UserUpdateRequestDTO("123", List.of("ADMIN")));
    }

}