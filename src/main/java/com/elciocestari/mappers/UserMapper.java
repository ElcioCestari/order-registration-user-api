package com.elciocestari.mappers;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
import com.elciocestari.entities.User;
import io.quarkus.arc.Unremovable;
import org.mapstruct.Mapper;

import javax.ws.rs.ext.Provider;
import java.util.List;

@Mapper(componentModel = "cdi")
@Unremovable
@Provider
public interface UserMapper {

    UserResponseDTO map(User entity);
    List<UserResponseDTO> map(List<User> entity);

    User map(UserRequestDTO dto);
}
