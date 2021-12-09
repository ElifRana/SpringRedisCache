package com.example.springrediscache.service;

import com.example.springrediscache.dto.UserRequest;
import com.example.springrediscache.model.User;

import java.util.List;

public interface UserCacheService {

    User getUser(int id);

    User createUser(User user);

    User updateUser(int id, UserRequest userRequest);

    void deleteUser(int id);

    List<User> getAll();

}
