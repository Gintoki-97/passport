package cn.gin.passport.module.web.controller;

import cn.gin.passport.common.Constants;
import cn.gin.passport.common.util.JsonObject;
import cn.gin.passport.common.util.Requests;
import cn.gin.passport.module.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class SiteController {

    @GetMapping({"", "/"})
    public String index(HttpServletResponse response) {

        final UserDetails currentUser = UserService.getCurrentUser();

        if (currentUser == null) {
            return Requests.redirect("/user/signin");
        }

        JsonObject json = new JsonObject(true, "Current user is signed in");
        json.addParam("user", currentUser);

        response.setContentType(Constants.CONTENT_TYPE_JSON);
        return json.toJson();
    }
}
