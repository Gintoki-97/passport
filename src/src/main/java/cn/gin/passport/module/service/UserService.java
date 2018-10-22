package cn.gin.passport.module.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserService {

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
