package com.myapp.cruddemo.service;

import com.myapp.cruddemo.dao.UserRepository;
import com.myapp.cruddemo.dto.UserRequestDTO;
import com.myapp.cruddemo.dto.UserResponseDTO;
import com.myapp.cruddemo.entity.Cart;
import com.myapp.cruddemo.entity.User;
import com.myapp.cruddemo.mapper.UserMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public ResponseEntity<?> register(UserRequestDTO userRequestDTO) {

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already exists");
        }

        User user = userMapper.dtoToEntity(userRequestDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Cart cart = new Cart();
        user.setCart(cart);

        User savedUser = userRepository.save(user);

        UserResponseDTO userResponseDTO =
                userMapper.entityToResponseDTO(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userResponseDTO);
    }
}