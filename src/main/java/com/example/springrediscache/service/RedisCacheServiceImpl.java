package com.example.springrediscache.service;

import com.example.springrediscache.dto.UserRequest;
import com.example.springrediscache.model.User;
import com.example.springrediscache.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisCacheServiceImpl implements UserCacheService {

    private final UserRepository userRepository;
    private final UserMapperService userMapperService;

    @Override
    //Cacheable: Önbelleğe alma davranışı etkinleştirir
    @Cacheable(cacheNames = "user", key = "#id")
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    //CacheEvict: Önbellek adreslerindeki kullanılmayan tüm girişleri temizleyecek ve yeni veriler için hazırlayacak
    //CachePut: Yöntemin yürütülmesine müdahale etmeden önbelleğin içeriğini güncelleyebiliriz.
    //Caching:Birden çok önbelleğe alma ek açıklamasını bu sayede gruplayabiliriz.
    @Caching(put = {@CachePut (value = "user")}, evict = {@CacheEvict(value = "user")})
    public User createUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isPresent()) {
            System.out.println("Error: Email is already taken!");
        }
        // isPresent:  isteğe bağlı durumda olan bir değer olup olmadığını anlamak için kullanılır
        if (userRepository.getByUserName(user.getUserName()).isPresent()) {
            System.out.println("Error: Email is already taken!");
        }

        User user1 = userMapperService.toDo(user);

        return userRepository.save(user1);
    }

    @Override
    @Caching(put = {@CachePut (value = "user")}, evict = {@CacheEvict(value = "user")})
    public User updateUser(int id, UserRequest userRequest) {

        User newUser = userRepository.findById(id).orElse(null);

        newUser.setUserName(userRequest.getUserName());
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());

        Optional<User> optionalUser = userRepository.getByUserName(userRequest.getUserName());

        if (newUser.getUserName() != userRequest.getUserName() && optionalUser.isPresent()) {
            System.out.println("Error: User Name is already taken!");
        }

        return userRepository.save(newUser);
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public void deleteUser(int id) {

        User user = userRepository.findById(id).orElse(null);

        userRepository.delete(user);
        System.out.println("User deleted");
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
