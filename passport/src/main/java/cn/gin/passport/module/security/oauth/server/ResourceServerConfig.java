package cn.gin.passport.module.security.oauth.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import cn.gin.passport.module.security.handler.AuthenticationFailureHandler;
import cn.gin.passport.module.security.handler.AuthenticationSuccessHandler;
import cn.gin.passport.module.security.oauth.client.SpringSocialConfigurer;
import cn.gin.passport.module.security.oauth.server.token.PassportAuthenticationEntryPoint;
import cn.gin.passport.module.security.oauth.server.token.PassportBearerTokenExtractor;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SpringSocialConfigurer socialSecurityConfig;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private PassportBearerTokenExtractor tokenExtractor;

    @Autowired
    private PassportAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/user/signin")
                .loginProcessingUrl("/user/signin")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/user", "/user/signin", "/user/oauth/*", "/user/me",
                        "/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(socialSecurityConfig)
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer configurer) throws Exception {

        configurer.tokenExtractor(tokenExtractor)
                  .authenticationEntryPoint(authenticationEntryPoint);

    }
}