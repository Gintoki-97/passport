package cn.gin.passport.common.util;

import cn.gin.passport.common.Constants;
import com.google.common.net.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * Http request related util class
 */
public class Requests {

    /**
     * Return whether a given <code>HttpServletRequest</code> is a AJax request
     *
     * @param req {@link HttpServletRequest}
     * @return Whether the given <code>HttpServletRequest</code> is a AJax request
     */
    public static boolean isAjax(HttpServletRequest req) {

        if (req == null) {
            return false;
        }

        return "XMLHttpRequest".equalsIgnoreCase(req.getHeader(HttpHeaders.X_REQUESTED_WITH));
    }

    /**
     * Generate the redirect URL by given URLs (Splicing URL)
     *
     * @param urls The URLs will redirect to
     * @return The redirect URL
     */
    public static String redirect(String... urls) {

        if (urls == null || urls.length == 0) {
            return Constants.Mark.SLASH;
        }

        StringBuilder builder = new StringBuilder("redirect:");

        for (String url : urls) {

            if (url.length() > 0) {
                url = url.trim().replaceAll(Constants.Mark.SLASH, Constants.Mark.EMPTY);
                builder.append(String.format("/%s", url));
            }
        }

        return builder.toString();
    }
}