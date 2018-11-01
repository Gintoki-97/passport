package cn.gin.passport.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    /**
     * The default cookie path of passport application. By default, the cookie path for the current application
     * is set to wandan. site so that authentication information can be uniformly obtained in all sub-domains
     */
    public static final String DEFAULT_DOMAIN = "wandan.site";

    public static final int DEFAULT_EXPIRY = 2592000;

    /**
     * Set a cookie on the specified response object
     *
     * @param response - {@link HttpServletResponse}
     * @param key - A <code>String</code> specifying the name of the cookie
     * @param value - A <code>String</code> specifying the value of the cookie
     * @param path - Specifies the domain within which this cookie should be presented
     * @param expiry - The maximum age of the cookie, specified in seconds
     */
    public static void setCookie(HttpServletResponse response, String key, String value, String path,
            int expiry) {

        if (response == null) {
            return;
        }

        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expiry);
        cookie.setDomain(DEFAULT_DOMAIN);
        cookie.setPath(path);

        response.addCookie(cookie);
    }

}
