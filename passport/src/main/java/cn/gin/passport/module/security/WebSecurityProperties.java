package cn.gin.passport.module.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import cn.gin.passport.module.security.oauth.client.SocialProperties;

@Configuration
@ConfigurationProperties(prefix = "security")
public class WebSecurityProperties {

    /**
     * Spring Social properties storage
     */
    private SocialProperties social = new SocialProperties();

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
