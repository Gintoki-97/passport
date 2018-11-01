package cn.gin.passport.module.security.oauth.client.github.service.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The entity class contains that the response user information that provided by Github OAuth API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUserDetails {

    /**
     * <p>The identifier for current user in Github</p>
     *
     * <p>Notice: The Github doesn't have the 'openid' field. It just likes openid to identify users who are logged in
     * via oauth authentication</p>
     */
    @JsonProperty("id")
    private int openid;

    /**
     * The nickname of current user in Github
     */
    @JsonProperty("name")
    private String nickname;

    /**
     * The avatar of current user in Github
     */
    @JsonProperty("avatar_url")
    private String avatarUrl;

    /**
     * The profile URL of current user in Github
     */
    @JsonProperty("html_url")
    private String profileUrl;

    public GithubUserDetails() {

    }

    public int getOpenid() {
        return openid;
    }

    public void setOpenid(int openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}