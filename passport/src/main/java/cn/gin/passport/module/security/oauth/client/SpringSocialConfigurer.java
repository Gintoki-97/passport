package cn.gin.passport.module.security.oauth.client;

import org.springframework.social.security.SocialAuthenticationFilter;

public class SpringSocialConfigurer extends org.springframework.social.security.SpringSocialConfigurer {

    /**
     * URL that spring social will intercept
     */
    private String filterProcessesUrl;

    public SpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T object) {

        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) object;
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}