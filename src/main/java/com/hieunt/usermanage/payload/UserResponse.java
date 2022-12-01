package com.hieunt.usermanage.payload;


import com.hieunt.usermanage.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private BigInteger total;

    private List<User> users;

    public UserResponse(BigInteger total, List<User> users) {
        this.total = total;
        this.users = users;
    }
}
