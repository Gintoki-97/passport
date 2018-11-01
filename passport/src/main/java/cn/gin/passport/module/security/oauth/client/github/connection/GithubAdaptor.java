package cn.gin.passport.module.security.oauth.client.github.connection;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import cn.gin.passport.module.security.oauth.client.github.service.api.GithubApi;
import cn.gin.passport.module.security.oauth.client.github.service.api.GithubUserDetails;

public class GithubAdaptor implements ApiAdapter<GithubApi> {

    @Override
    public boolean test(GithubApi api) {

        return true;
    }

    /**
     * Convert the Github response data to local {@link GithubUserDetails}
     */
    @Override
    public void setConnectionValues(GithubApi api, ConnectionValues values) {

        GithubUserDetails userDetails = api.getGithubUserDetails();
        values.setProviderUserId(String.valueOf(userDetails.getOpenid()));
        values.setDisplayName(userDetails.getNickname());
        values.setImageUrl(userDetails.getAvatarUrl());
        values.setProfileUrl(userDetails.getProfileUrl());
    }

    @Override
    public UserProfile fetchUserProfile(GithubApi api) {

        return null;
    }

    @Override
    public void updateStatus(GithubApi api, String message) {

    }

}
