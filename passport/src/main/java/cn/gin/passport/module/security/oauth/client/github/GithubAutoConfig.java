package cn.gin.passport.module.security.oauth.client.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

import cn.gin.passport.module.security.WebSecurityProperties;
import cn.gin.passport.module.security.oauth.client.SocialConfig;
import cn.gin.passport.module.security.oauth.client.SocialProperties;
import cn.gin.passport.module.security.oauth.client.github.connection.GithubConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "security.social.github", name="app-id")
public class GithubAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SocialConfig socialConfig;

    @Autowired
    private WebSecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        SocialProperties social = securityProperties.getSocial();

        return new GithubConnectionFactory(social.getGithub().getProviderId(),
                social.getGithub().getAppId(), social.getGithub().getAppSecret());
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

        return socialConfig.getUsersConnectionRepository(connectionFactoryLocator);
    }
}