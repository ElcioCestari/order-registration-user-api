package com.elciocestari.mappers;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
import com.elciocestari.dtos.UserUpdateRequestDTO;
import com.elciocestari.entities.User;
import io.quarkus.arc.Unremovable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import javax.ws.rs.ext.Provider;
import java.util.List;

@Mapper(componentModel = "cdi")
@Unremovable
@Provider
public interface UserMapper {

    UserResponseDTO map(User entity);

    List<UserResponseDTO> map(List<User> entity);

    User map(UserRequestDTO dto);

    User map(UserUpdateRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    User merge(UserUpdateRequestDTO dto, @MappingTarget User user);
}
