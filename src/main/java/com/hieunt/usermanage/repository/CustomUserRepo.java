package com.hieunt.usermanage.repository;

import com.hieunt.usermanage.entity.User;
import com.hieunt.usermanage.payload.UserResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomUserRepo  {
    public UserResponse searchByUser (String search , Integer page , Integer size) ;


}
