package cn.gin.passport.module.service;

import cn.gin.passport.module.dao.UserDao;
import cn.gin.passport.module.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    // Service Method

    public User save(User user) {

        return userDao.save(user);
    }

    public User get(Integer id) {

        return userDao.getById(id);
    }

    public User loadByAccount(String account) {

        return userDao.getByAccount(account);
    }

    //// Private Method

    /**
     * Return whether current user is authenticated
     *
     * @return Whether current user is authenticated
     */
    public static boolean isAuthenticated() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (authentication.isAuthenticated()) &&
                !(authentication instanceof AnonymousAuthenticationToken);
    }

    /**
     * Get current authenticated user
     *
     * @return {@link UserDetails}
     */
    public static UserDetails getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = null;

        if (authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {

            try {
                userDetails = (UserDetails) authentication.getPrincipal();
            } catch (Exception exception) {
                // Nothing
            }
        }

        return userDetails;
    }
}
