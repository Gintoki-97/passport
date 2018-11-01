package cn.gin.passport.module.security.oauth.client;

import cn.gin.passport.module.security.oauth.client.github.GithubProperties;
import cn.gin.passport.module.security.oauth.client.wechat.WeChatProperties;

/**
 * Spring Social properties storage
 */
public class SocialProperties {

    /**
     * URL that spring social will intercept, the default URL prefix is '/user/oauth'
     */
    private String filterProcessesUrl = "/user/oauth";

    /**
     * WeChat properties storage
     */
    private WeChatProperties weChat = new WeChatProperties();

    /**
     * Github properties storage
     */
    private GithubProperties github = new GithubProperties();

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public WeChatProperties getWeChat() {
        return weChat;
    }

    public void setWeChat(WeChatProperties weChat) {
        this.weChat = weChat;
    }

    public GithubProperties getGithub() {
        return github;
    }

    public void setGithub(GithubProperties github) {
        this.github = github;
    }
}