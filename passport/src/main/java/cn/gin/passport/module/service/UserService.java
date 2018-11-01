package cn.gin.passport.module.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cn.gin.passport.module.dao.UserRepository;
import cn.gin.passport.module.entity.User;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User signup(User user) {

        Date date = new Date();

        user.setId(null);
        user.setCreated(date);
        user.setUpdated(date);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setTemporary(0);

        return userRepository.save(user);
    }

    /**
     * Return the user object by given account
     *
     * @param account User account
     * @return The user object by given account
     */
    public User getByAccount(String account) {

        return userRepository.getByAccount(account);
    }

    //// Helper Method

    /**
     * Return whether current user is authenticated
     *
     * @return Whether current user is authenticated
     */
    public static boolean isAuthenticated() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (authentication.isAuthenticated()) && !(authentication instanceof AnonymousAuthenticationToken);
    }

    /**
     * Get current authenticated user
     *
     * @return {@link UserDetails}
     */
    public static UserDetails getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = null;

        if (authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {

            try {
                userDetails = (UserDetails) authentication.getPrincipal();
            } catch (Exception exception) {
                // Nothing
            }
        }

        return userDetails;
    }
}
