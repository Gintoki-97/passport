package cn.gin.passport.module.entity;

import org.assertj.core.util.Lists;
import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Entity for application user
 */
@Entity
public class User implements UserDetails, Principal {

    /**
     * Identifier for each user
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * <p>Application authentication account</p>
     *
     * <tt>Reserved Annotation means that at present, there is no custom authentication module, which only
     * supports temporary use and third party login</tt>
     *
     * @Reserved
     */
    private String account;

    /**
     * Application authentication password
     *
     * @Reserved
     */
    private String password;

    /**
     * User's name displayed at the frontend after authentication, not the unique.
     */
    private String nickname;

    /**
     * User avatar
     */
    private String avatar;

    /**
     * <p>Indicates whether the current user is a temporary user</p>
     *
     * <pre>
     *  temporary = 1    Temporary
     *  temporary = 0    Not temporary (Means Authenticated)
     * </pre>
     */
    private Integer temporary;

    /**
     * Created timestamp
     */
    private Date created;

    /**
     * Updated timestamp
     */
    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getTemporary() {
        return temporary;
    }

    public void setTemporary(Integer temporary) {
        this.temporary = temporary;
    }

    /**
     * Indicates whether the current user is a temporary user
     *
     * @return Whether the current user is a temporary user
     */
    public Boolean isTemporary() {

        return temporary == 1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return this.account;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return this.account;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}