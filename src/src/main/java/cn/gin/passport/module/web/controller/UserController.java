package cn.gin.passport.module.web.controller;

import cn.gin.passport.common.Constants;
import cn.gin.passport.common.util.JsonObject;
import cn.gin.passport.common.util.Requests;
import cn.gin.passport.module.entity.User;
import cn.gin.passport.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(Constants.Path.CTRL_USER)
public class UserController {

    @Autowired
    private UserService userService;

    //// View Request

    @GetMapping(Constants.Path.CTRL_USER_SIGNIN)
    public String signinViewRequest() {

        if (UserService.isAuthenticated()) {

            return Requests.redirect(Constants.Path.CTRL_USER);
        }

        return Constants.Path.VIEW_SIGNIN;
    }

    //// Handler Request

    //// REST Interface

    @GetMapping({Constants.Mark.EMPTY, Constants.Mark.SLASH})
    @ResponseBody
    public String currentUser() {

        JsonObject jsonObject = new JsonObject();
        UserDetails currentUser = UserService.getCurrentUser();

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

    //// Private Method

    private String doAuthenticated(HttpServletRequest request, User user) {

        final User loaded = userService.loadByAccount(user.getAccount());

        if (loaded != null) {
            if (loaded.getPassword().equals(user.getPassword())) {
                return Requests.redirect(Constants.Path.CTRL_USER);
            }
        }

        return Requests.redirect(Constants.Path.CTRL_USER, Constants.Path.CTRL_USER_SIGNIN);
    }
}
