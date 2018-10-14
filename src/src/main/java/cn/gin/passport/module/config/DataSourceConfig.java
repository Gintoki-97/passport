package cn.gin.passport.module.config;

import cn.gin.passport.common.Constants;
import cn.gin.passport.common.util.Booleans;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

    /**
     * Full classname of Database driver class
     */
    private String driverClassName;

    /**
     * JDBC Connection URL
     */
    private String url;

    /**
     * Database username
     */
    private String username;

    /**
     * Database password
     */
    private String password;


    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Bean definition of {@link DataSource}
     *
     * @return {@link DataSource}
     */
    @Bean
    public DataSource dataSource() {

        DataSourceBuilder builder = DataSourceBuilder.create();

        if (!Booleans.boolAnd(driverClassName, url)) {
            builder.driverClassName(Constants.Setting.DATASOURCE_DRIVER);
            builder.url(Constants.Setting.DATASOURCE_URL);
            builder.type(SQLiteDataSource.class);
        } else {
            builder.driverClassName(driverClassName);
            builder.url(url);
            builder.username(username);
            builder.password(password);
        }

        return builder.build();
    }
}