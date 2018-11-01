package cn.gin.passport.module.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.collect.Lists;

import cn.gin.passport.module.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Before
    public void init() {
        System.out.println("Test init -----------------");
    }

    @After
    public void after() {
        System.out.println("Test over -----------------");
    }

    @Test
    public void testSignup() {

        User user = new User("root", "111", Lists.newArrayListWithCapacity(0));
        User saved = userService.signup(user);
        assert saved != null;
    }
}
