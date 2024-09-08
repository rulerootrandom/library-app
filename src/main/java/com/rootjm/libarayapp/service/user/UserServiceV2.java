package com.rootjm.libarayapp.service.user;

import com.rootjm.libarayapp.controller.user.request.UserUpdateRequest;
import com.rootjm.libarayapp.domain.user.User;
import com.rootjm.libarayapp.domain.user.UserRepository;
import com.rootjm.libarayapp.dto.user.request.UserCreateRequest;
import com.rootjm.libarayapp.dto.user.response.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request) {
        User u = userRepository.save(new User(request.getName(), request.getAge()));

    }

    @Transactional
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();

        /*
        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());
        */

        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {

        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name) {

        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }
}
