package cn.gin.passport.module.security.oauth.client;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * Execute the auto sign up logic for a new OAuth user
 */
@Component
public class AutoConnectionSignup implements ConnectionSignUp {

    /**
     * When a new user logs in to the system through OAuth, a new user is automatically
     * created for it, eliminating the need for the user to manually register or bind
     *
     * @param connection {@link Connection}
     * @return The User ID of the newly created
     */
    @Override
    public String execute(Connection<?> connection) {

        return connection.getKey().getProviderUserId();
    }
}
