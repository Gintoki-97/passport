package cn.gin.passport.common.util;

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

        return "XMLHttpRequest".equals(req.getHeader(HttpHeaders.X_REQUESTED_WITH));
    }
}