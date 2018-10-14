package cn.gin.passport.common;

public class Constants {

    public static class Setting {

        public static final String DATASOURCE_DRIVER = "org.sqlite.JDBC";
        public static final String DATASOURCE_URL = "jdbc:sqlite:passport";
    }

    public static class Path {

        public static final String CTRL_SITE_HELLO = "/hello";
        public static final String CTRL_USER_SIGNUP = "/signup";
    }
}