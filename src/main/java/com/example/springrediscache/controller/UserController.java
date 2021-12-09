package com.example.springrediscache.controller;

import com.example.springrediscache.dto.UserRequest;
import com.example.springrediscache.model.User;
import com.example.springrediscache.service.UserCacheService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserCacheService userCacheService;

    @Autowired
    public UserController(UserCacheService userCacheService) {
        this.userCacheService = userCacheService;
    }

    @GetMapping("/user/{id}")
    public User getUser(@ApiParam(type = "int", value = "Existing person ID", required = true) @PathVariable int id) {
        return userCacheService.getUser(id);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userCacheService.createUser(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") int id, @RequestBody UserRequest userRequest) {
        return userCacheService.updateUser(id, userRequest);
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userCacheService.deleteUser(id);
    }

    @GetMapping("/users")
    public List<User> getAll() {
        return userCacheService.getAll();
    }

}
