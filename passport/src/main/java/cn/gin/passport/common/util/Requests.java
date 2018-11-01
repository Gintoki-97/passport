package cn.gin.passport.common.util;

import javax.servlet.http.HttpServletRequest;

import com.google.common.net.HttpHeaders;

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
            return "/";
        }

        StringBuilder builder = new StringBuilder("redirect:");

        for (String url : urls) {

            final int len = url.length();

            if (len > 0) {
                url = url.trim();

                if (url.charAt(0) == '/' && len > 1)
                    url = url.substring(1);

                if (url.charAt(len - 1) == '/' && len > 1)
                    url = url.substring(0, len - 2);

                builder.append(String.format("/%s", url));
            }
        }

        return builder.toString();
    }
}