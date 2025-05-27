package com.example.tourManagement.service.impl;

import com.example.tourManagement.dto.RequestDTO;
import com.example.tourManagement.dto.ResponseDTO;
import com.example.tourManagement.dto.UserDTO;
import com.example.tourManagement.entity.User;
import com.example.tourManagement.repository.UserRepository;
import com.example.tourManagement.service.interf.UserService;
import com.example.tourManagement.utils.JWTUtils;
import com.example.tourManagement.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public ResponseDTO registerUser(User user) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (user.getUserRole() == null || user.getUserRole().isBlank()) {
                user.setUserRole("USER");
            }

            if (userRepository.existsByUserEmail(user.getUserEmail())) {
                throw new Exception(user.getUserEmail() + " already exists!");
            }

            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            User savedUser = userRepository.save(user);
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(savedUser);

            responseDTO.setStatusCode(200);
            responseDTO.setUserDTO(userDTO);

        } catch (IllegalArgumentException e) {
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        } catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage("Internal server error");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO loginUser(RequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUserEmail(), requestDTO.getUserPassword()));
            var user = userRepository.findByUserEmail(requestDTO.getUserEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

            var token = jwtUtils.generateToken(user);
            responseDTO.setStatusCode(200);
            responseDTO.setToken(token);
            responseDTO.setUserRole(user.getUserRole());
            responseDTO.setExpirationTime("1 hour");
            responseDTO.setMessage("Successful!!!");

        } catch (IllegalArgumentException e ){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        } catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage("Internal server error");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO getUserById(Integer userId) {
        return null;
    }

    @Override
    public ResponseDTO getAllUsers() {
        return null;
    }

    @Override
    public ResponseDTO updateUser(Integer userId, UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseDTO deleteUser(Integer userId) {
        return null;
    }
}
