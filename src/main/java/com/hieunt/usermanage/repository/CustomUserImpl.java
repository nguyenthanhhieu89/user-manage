package com.hieunt.usermanage.repository;

import com.hieunt.usermanage.entity.User;
import com.hieunt.usermanage.payload.UserResponse;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

@Repository
public class CustomUserImpl implements CustomUserRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserResponse searchByUser(String search, Integer page, Integer size) {
        StringBuilder queryUser = new StringBuilder("SELECT * FROM user ");
        StringBuilder queryTotal = new StringBuilder("SELECT COUNT(*) FROM user ");
        if (StringUtils.hasText(search)) {
            queryUser.append("Where user.name LIKE '%").append(search).append("%'").append(" OR user.address LIKE '%").append(search).append("%'");
            queryTotal.append("Where user.name LIKE '%").append(search).append("%'").append(" OR user.address LIKE '%").append(search).append("%'");
        }

        // Get list User
        //--------------------------------------------------
        Query userQuery = entityManager.createNativeQuery(queryUser.toString(), User.class);
        // Paging
        userQuery.setFirstResult(page * size);
        userQuery.setMaxResults(size);
        //--------------------------------------------------

        // Get total
        //--------------------------------------------------
        Query total = entityManager.createNativeQuery(queryTotal.toString());
        //--------------------------------------------------

        // Build result
        //--------------------------------------------------
        UserResponse response = new UserResponse();
        response.setUsers(userQuery.getResultList());
        response.setTotal((BigInteger) total.getSingleResult());
        //--------------------------------------------------
        return response;
    }
}
