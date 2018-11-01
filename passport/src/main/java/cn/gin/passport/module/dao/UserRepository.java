package cn.gin.passport.module.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.gin.passport.module.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Return the user object by given account
     *
     * @param account User account
     * @return The user object by given account
     */
    User getByAccount(String account);
}