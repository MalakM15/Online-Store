package com.myapp.cruddemo.mapper;
import com.myapp.cruddemo.dto.UserRequestDTO;
import com.myapp.cruddemo.dto.UserResponseDTO;
import com.myapp.cruddemo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO entityToResponseDTO(User user);

    User dtoToEntity(UserRequestDTO userRequestDTO);
}
