package cn.gin.passport.module.web.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gin.passport.common.util.JsonObject;
import cn.gin.passport.common.util.Requests;
import cn.gin.passport.module.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    // ~ View Request

    @GetMapping("/signin")
    public String signinViewRequest() {

        if (UserService.isAuthenticated()) {
            return Requests.redirect("/user/me");
        }

        return "signin";
    }

    // ~ Handler Request



    // ~ Rest Interface

    @GetMapping("/me")
    @ResponseBody
    public String me() {

        UserDetails currentUser = UserService.getCurrentUser();
        JsonObject jsonObject = new JsonObject();

        if (currentUser == null) {
            jsonObject.setCode(1);
            jsonObject.setSuccess(false);
            jsonObject.setMsg("No user signed in");
        } else {
            jsonObject.setCode(0);
            jsonObject.setSuccess(true);
            jsonObject.setMsg("Request success");
            jsonObject.addParam("user", currentUser);
        }

        return jsonObject.toJson();
    }

}
