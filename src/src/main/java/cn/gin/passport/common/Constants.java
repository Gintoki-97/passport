package cn.gin.passport.common;

public class Constants {

    public static class Http {

        public static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    }

    public static class System {

        public static final String DEFAULT_CHARSET = "UTF-8";
    }

    public static final class Mark {

        public static final String EMPTY = "";
        public static final String SLASH = "/";
        public static final String BACKSLASH = "\\";
        public static final String DOT = ".";
        public static final String SPACING = " ";
        public static final String ZERO = "0";
        public static final String COLON = ":";
    }

    public static class Setting {

        public static final String DATASOURCE_DRIVER = "org.sqlite.JDBC";
        public static final String DATASOURCE_URL = "jdbc:sqlite:passport";
    }

    public static class Path {

        public static final String WILDCARD = "/*";

        public static final String CTRL_USER = "/user";
        public static final String CTRL_USER_SIGNIN = "/signin";

        public static final String VIEW_SIGNIN = "signin";
    }
}