package cn.gin.passport.module.config;

import cn.gin.passport.common.util.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    /**
     * The key used when storing the current class-related properties or objects
     */
    public static final String SELF_KEY = ApplicationProperties.class.getName();

    /**
     * The protocol current server used, default is HTTP
     */
    private String protocol = "http";

    /**
     * Server host
     */
    private String host = "127.0.0.1";

    /**
     * Server port
     */
    private String port = "8000";

    /**
     * The whole path of the server, contains protocol, host and port
     */
    private String server;

    /**
     * Contains the basic information about current application
     */
    private InfoProperties info = new InfoProperties();

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public InfoProperties getInfo() {
        return info;
    }

    public void setInfo(InfoProperties info) {
        this.info = info;
    }

    public String getServer() {

        if (StringUtils.isEmpty(this.server)) {
            this.server = new StringBuilder(this.protocol).append("://").append(this.host)
                    .append(":").append(this.port).toString();
        }

        return this.server;
    }

    /**
     * Contains the basic information about current application
     */
    public class InfoProperties {

        private String name;
        private String description;
        private String created;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }
    }
}
