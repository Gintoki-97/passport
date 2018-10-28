package cn.gin.passport.module.security.service;

import cn.gin.passport.module.entity.User;
import cn.gin.passport.module.service.UserService;
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
import org.springframework.stereotype.Component;

@Component
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

        logger.info("Accept the form login request by username: {}", username);
        User user = userService.loadByAccount(username);

        if (user == null) {
            throw new UsernameNotFoundException("Cannot found corresponding user by the username: " + username);
        }

        return user;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

        logger.info("Accept the OAuth2 login request by user ID:", userId);

        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {

        String password = passwordEncoder.encode("111");

        logger.info("The passport of current login user = {}", password);

        return new SocialUser(userId, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin, ROLE_USER"));
    }
}