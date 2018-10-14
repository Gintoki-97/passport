package cn.gin.passport.module.web.controller;

import cn.gin.passport.common.util.JsonObject;
import cn.gin.passport.module.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    //// REST interface

    @GetMapping({"", "/"})
    public String currentUser() {

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
