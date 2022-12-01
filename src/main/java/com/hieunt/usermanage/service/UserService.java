package com.hieunt.usermanage.service;

import com.github.javafaker.Faker;
import com.hieunt.usermanage.entity.User;
import com.hieunt.usermanage.payload.UserResponse;
import com.hieunt.usermanage.repository.CustomUserRepo;
import com.hieunt.usermanage.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    CustomUserRepo customUserRepo;
    @PostConstruct
    public void init() {
        if (userRepo.findAll().size() > 0) {
            return;
        }
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setName(faker.name().firstName());
            user.setAddress(faker.address().streetAddress());
            user.setGender("Male");
            user.setDateOfBirth(new Date());
            users.add(user);
        }
        userRepo.saveAll(users);

    }

    public User createUser(User userClient) { // user lay thong tin tu client
        return userRepo.save(userClient);
    }

    public UserResponse getUser(String search, int page, int size) {

       return customUserRepo.searchByUser(search , page , size);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }


    public User updateUser(User request) {
        Optional<User> userDB = userRepo.findById(request.getId());
        if (userDB.isEmpty()) {
            return null;
        }
        // lay thong tin user trong DB
        User user = userDB.get();
        // Copy cac
        user.setName(request.getName());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setDateOfBirth(request.getDateOfBirth());

        return userRepo.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> userDB = userRepo.findById(id);
        if (userDB.isEmpty()) {
            return null;
        }
        return userDB.get();
    }
}
