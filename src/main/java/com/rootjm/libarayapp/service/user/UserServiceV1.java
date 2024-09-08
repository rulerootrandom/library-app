package com.rootjm.libarayapp.service.user;

import com.rootjm.libarayapp.controller.user.request.UserUpdateRequest;
import com.rootjm.libarayapp.dto.user.request.UserCreateRequest;
import com.rootjm.libarayapp.dto.user.response.UserResponse;
import com.rootjm.libarayapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {

    private final UserJdbcRepository userRepository;

    public UserServiceV1(UserJdbcRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void updateUser(UserUpdateRequest request) {

        boolean isUserNotExist = userRepository.isUserNotExist(request.getId());

        if(isUserNotExist) {
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {

        if(userRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

        userRepository.deleteUser(name);
    }

    public void saveUser(UserCreateRequest request) {

        userRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {

        return userRepository.getUsers();
    }
}
