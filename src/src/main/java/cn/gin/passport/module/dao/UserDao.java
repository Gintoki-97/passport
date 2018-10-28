package cn.gin.passport.module.dao;

import cn.gin.passport.module.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * Get a user object by given user ID
     *
     * @param id The user ID given
     * @return Specified user object
     */
    User getById(Integer id);

    /**
     * Get a user object by given user account
     *
     * @param account The user account given
     * @return Specified user object
     */
    @Query("FROM cn.gin.passport.module.entity.User user WHERE user.account=?1")
    User getByAccount(String account);
}
