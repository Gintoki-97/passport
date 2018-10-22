package cn.gin.passport.module.entity;

import java.util.Date;

/**
 * Entity for application user
 */
public class User {

    /**
     * Identifier for each user
     */
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

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
}