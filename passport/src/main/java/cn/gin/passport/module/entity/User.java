package cn.gin.passport.module.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.google.common.collect.Lists;

/**
 * Entity for application user
 */
@Entity
@Table(name = "user")
public class User extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    /**
     * Identifier for each user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     *     temporary = 1    Temporary
     *     temporary = 0    Not temporary (Means Authenticated)
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

    /**
     * Adaptor field
     */
    private String username;

    public User() {
        super("UNSET", "UNSET", Lists.newArrayListWithCapacity(0));
    }

    public User(String username, String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.setUsername(username);
        this.setPassword(password);
    }

    public User(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.setUsername(username);
        this.setPassword(password);
    }


    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;

        if (username != null && this.account == null) {
            setAccount(username);
        }
    }

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

        if (account != null && this.username == null) {
            setUsername(account);
        }
    }

    @Override
    public String getPassword() {
        return password;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", account=" + account + ", password=" + password + ", nickname=" + nickname
                + ", temporary=" + temporary + ", created=" + created + ", updated=" + updated + "]";
    }
}