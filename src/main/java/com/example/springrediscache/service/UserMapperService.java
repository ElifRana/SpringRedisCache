package com.example.springrediscache.service;

import com.example.springrediscache.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapperService {

    User toDo(User user);
}
