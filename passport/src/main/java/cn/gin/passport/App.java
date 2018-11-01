package cn.gin.passport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.gin.passport")
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
}
