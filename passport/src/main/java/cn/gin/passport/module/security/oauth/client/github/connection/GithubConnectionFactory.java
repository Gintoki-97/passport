package cn.gin.passport.module.security.oauth.client.github.connection;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

import cn.gin.passport.module.security.oauth.client.github.service.GithubServiceProvider;
import cn.gin.passport.module.security.oauth.client.github.service.api.GithubApi;

/**
 * Factory classes for building OAuth Connection
 */
public class GithubConnectionFactory extends OAuth2ConnectionFactory<GithubApi> {

    public GithubConnectionFactory(String providerId, String clientId, String clientSecret) {
        super(providerId, new GithubServiceProvider(clientId, clientSecret), new GithubAdaptor());
    }

    @Override
    public Connection<GithubApi> createConnection(ConnectionData data) {

        return new OAuth2Connection<GithubApi>(data, getOAuth2ServiceProvider(),
                getApiAdapter());
    }

    private OAuth2ServiceProvider<GithubApi> getOAuth2ServiceProvider() {

        return (OAuth2ServiceProvider<GithubApi>) getServiceProvider();
    }
}