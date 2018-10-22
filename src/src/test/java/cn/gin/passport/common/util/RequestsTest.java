package cn.gin.passport.common.util;

import org.junit.Assert;
import org.junit.Test;

public class RequestsTest {

    @Test
    public void testRedirect() {
        String url = Requests.redirect("/user", "/signin");
        Assert.assertEquals("The redirect is matched", "redirect:/user/signin", url);
    }
}