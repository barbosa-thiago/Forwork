package com.thiago.forwork.mapper;

import com.thiago.forwork.dto.user.UserResponseFullDTO;
import com.thiago.forwork.dto.user.UserSaveDTO;
import com.thiago.forwork.dto.user.UserTimeResponseDTO;
import com.thiago.forwork.dto.user.UserUpdateDTO;
import com.thiago.forwork.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TaskMapper.class, DepartmentMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "department", target = "department.id")
    User userSaveDTOToUser(UserSaveDTO userSaveDTO);

    UserResponseFullDTO userToUserResponseDTO(User user);

    UserTimeResponseDTO userToUserTimeResponseDTO(User user);

    @IterableMapping(elementTargetType = User.class)
    List<UserResponseFullDTO> usersToUserResponseDTOs(List<User> users);

    default Page<UserResponseFullDTO> usersToUserResponseDTOs(Page<User> users) {
        return users.map(this::userToUserResponseDTO);
    }

    default Page<UserTimeResponseDTO> usersToUserTimeResponseDTOs(Page<User> users) {
        return users.map(this::userToUserTimeResponseDTO);
    }

    @Mapping(source = "department", target = "department.id")
    @Mapping(source = "tasks", target = "tasks", ignore = true)
    void update(UserUpdateDTO userUpdateDTO, @MappingTarget User user);
}
