package cn.gin.passport.module.security.oauth.client.github.service.api;

/**
 * Spring Social api interface implementation for Github
 */
public interface GithubApi {

    /**
     * <p>Get the Github user information</p>
     *
     * <p>For Github's OAuth API, each access token uniquely identifies a user. Therefore, for the OAuth
     * interface provided by Github to get user information, only the access token corresponding to the
     * user needs to be passed in, and the basic information corresponding to the user can be obtained.</p>
     *
     * @return The specified Github user information
     */
    GithubUserDetails getGithubUserDetails();
}