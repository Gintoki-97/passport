package cn.gin.passport.module.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import cn.gin.passport.module.entity.User;
import cn.gin.passport.module.service.UserService;

@Service
public class PassportUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    /**
     * The default root logger
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.debug("Accept the form login request for username: {}", username);
        User user = userService.getByAccount(username);

        if (user == null) {
            throw new UsernameNotFoundException("Cannot find the user with username: " + username);
        }

        return user;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

        logger.info("Accept the social login request for user-id: {}", userId);

        return this.createUser(userId);
    }

    private SocialUserDetails createUser(String userId) {

        String password = passwordEncoder.encode("123456");
        logger.info("Create the user with user-id: {} and password: {}", userId, password);

        return new SocialUser(userId, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

}
