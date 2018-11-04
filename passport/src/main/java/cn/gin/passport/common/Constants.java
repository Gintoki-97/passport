package cn.gin.passport.common;

public interface Constants {

    // ~ Ordinary literal string

    String CHARSET_UTF8 = "UTF-8";
    String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    /**
     * ~ Commonly used marks
     */
    interface Mark {

        /**
         * Empty string
         */
        String EMPTY = "";
    }

    /**
     * ~ Application cookies key
     */
    interface Cookie {

        /**
         * Authorize cookie key. Used to identify the user's identity
         */
        String ACCESS_TOKEN = "TOKEN";
    }

    interface Path {

    }

    interface Attr {

    }
}