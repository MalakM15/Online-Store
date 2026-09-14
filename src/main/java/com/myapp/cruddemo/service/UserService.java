package com.myapp.cruddemo.service;

import com.myapp.cruddemo.dao.UserRepository;
import com.myapp.cruddemo.dto.UserResponseDTO;
import com.myapp.cruddemo.entity.User;
import com.myapp.cruddemo.mapper.UserMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ResponseEntity<?> getAllUsers() {

        List<User> users = userRepository.findAll();

        List<UserResponseDTO> responseDTOs = users.stream()
                .map(userMapper::entityToResponseDTO)
                .toList();

        return ResponseEntity.ok(responseDTOs);
    }

    public ResponseEntity<?> getUserById(int id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with id " + id + " not found");
        }

        UserResponseDTO responseDTO =
                userMapper.entityToResponseDTO(user);

        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<?> deleteUser(int id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with id " + id + " not found");
        }

        userRepository.delete(user);

        return ResponseEntity.ok("User deleted successfully");
    }
}