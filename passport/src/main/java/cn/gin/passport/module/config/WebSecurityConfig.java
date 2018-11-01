package cn.gin.passport.module.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/user", "/user/signin", "/user/me").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/user/signin")
                .loginProcessingUrl("/user/signin")
            .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
